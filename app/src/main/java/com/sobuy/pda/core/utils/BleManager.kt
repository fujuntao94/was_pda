package com.sobuy.pda.core.utils

import android.content.Context
import com.polidea.rxandroidble3.RxBleConnection
import com.polidea.rxandroidble3.RxBleClient
import com.polidea.rxandroidble3.RxBleDevice
import com.polidea.rxandroidble3.scan.ScanSettings
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.UUID

// BleDevice.kt
data class BleDevice(
    val macAddress: String,
    val name: String? = null,
    val rssi: Int = 0,
    val advertisementData: ByteArray? = null
)

// BleService.kt
data class BleService(
    val uuid: String,
    val characteristics: List<BleCharacteristic>
)

// BleCharacteristic.kt
data class BleCharacteristic(
    val uuid: String,
    val properties: Int,
    val descriptors: List<BleDescriptor> = emptyList()
)

// BleDescriptor.kt
data class BleDescriptor(
    val uuid: String
)

// BleConnectionState.kt
enum class BleConnectionState {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    DISCONNECTING,
    SERVICE_DISCOVERED
}

class BleManager private constructor(context: Context) {
    private val rxBleClient: RxBleClient = RxBleClient.create(context)
    private var rxBleDevice: RxBleDevice? = null
    private var connection: RxBleConnection? = null
    private var connectionDisposable: Disposable? = null
    private var scanDisposable: Disposable? = null
    private val _connectionState = MutableStateFlow(BleConnectionState.DISCONNECTED)
    val connectionState: StateFlow<BleConnectionState> = _connectionState

    // 设备扫描结果流
    private val _scannedDevices = MutableStateFlow<List<BleDevice>>(emptyList())
    val scannedDevices: StateFlow<List<BleDevice>> = _scannedDevices

    // 连接错误流
    private val _connectionErrors = MutableSharedFlow<String>()
    val connectionErrors: SharedFlow<String> = _connectionErrors

    // 特征值变化流
    private val _characteristicChanges = MutableSharedFlow<Pair<String, ByteArray>>()
    val characteristicChanges: SharedFlow<Pair<String, ByteArray>> = _characteristicChanges

    companion object {
        @Volatile
        private var instance: BleManager? = null

        fun getInstance(context: Context): BleManager {
            return instance ?: synchronized(this) {
                instance ?: BleManager(context.applicationContext).also { instance = it }
            }
        }
    }

    // 开始扫描
    fun startScan() {
        if (scanDisposable != null) return

        val scanResults = mutableListOf<BleDevice>()
        _scannedDevices.value = emptyList()

        scanDisposable = rxBleClient.scanBleDevices(
            ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build()
        ).subscribe(
            { scanResult ->
                val device = BleDevice(
                    macAddress = scanResult.bleDevice.macAddress,
                    name = scanResult.bleDevice.name,
                    rssi = scanResult.rssi,
                    advertisementData = scanResult.scanRecord?.bytes
                )

                // 去重添加
                if (!scanResults.any { it.macAddress == device.macAddress }) {
                    scanResults.add(device)
                    _scannedDevices.value = scanResults.toList()
                }
            },
            { throwable ->
                _connectionErrors.tryEmit("扫描错误: ${throwable.message}")
                stopScan()
            }
        )
    }

    // 停止扫描
    fun stopScan() {
        scanDisposable?.dispose()
        scanDisposable = null
    }

    // 连接设备（协程版本）
    suspend fun connect(deviceAddress: String, autoConnect: Boolean = false): Boolean = suspendCancellableCoroutine { continuation ->
        if (_connectionState.value == BleConnectionState.CONNECTED) {
            disconnect()
        }

        _connectionState.value = BleConnectionState.CONNECTING
        rxBleDevice = rxBleClient.getBleDevice(deviceAddress)

        connectionDisposable = rxBleDevice!!.establishConnection(autoConnect)
            .doFinally { _connectionState.value = BleConnectionState.DISCONNECTED }
            .subscribe(
                { rxBleConnection ->
                    connection = rxBleConnection
                    _connectionState.value = BleConnectionState.CONNECTED
                    continuation.resume(true) { cause ->
                        disconnect()
                    }
                },
                { throwable ->
                    _connectionState.value = BleConnectionState.DISCONNECTED
                    _connectionErrors.tryEmit("连接失败: ${throwable.message}")
                    continuation.resume(false) { cause ->
                        disconnect()
                    }
                }
            )

        // 取消协程时断开连接
        continuation.invokeOnCancellation {
            disconnect()
        }
    }

    // 断开连接
    fun disconnect() {
        connectionDisposable?.dispose()
        connectionDisposable = null
        connection = null
        _connectionState.value = BleConnectionState.DISCONNECTED
    }

    // 发现服务（协程版本）
    suspend fun discoverServices(): List<BleService> = suspendCancellableCoroutine { continuation ->
        val connection = connection ?: run {
            _connectionErrors.tryEmit("未连接到设备")
            continuation.resume(emptyList()) { cause -> }
            return@suspendCancellableCoroutine
        }

        connection.discoverServices()
            .subscribe(
                { bleGattServices ->
//                    val services = bleGattServices.services.map { service ->
//                        val characteristics = service.characteristics.map { characteristic ->
//                            val descriptors = characteristic.descriptors.map { descriptor ->
//                                BleDescriptor(descriptor.uuid.toString())
//                            }
//                            BleCharacteristic(
//                                uuid = characteristic.uuid.toString(),
//                                properties = characteristic.properties,
//                                descriptors = descriptors
//                            )
//                        }
//                        BleService(
//                            uuid = service.uuid.toString(),
//                            characteristics = characteristics
//                        )
//                    }

                    _connectionState.value = BleConnectionState.SERVICE_DISCOVERED
//                    continuation.resume(services) { cause -> }
                },
                { throwable ->
                    _connectionErrors.tryEmit("服务发现失败: ${throwable.message}")
                    continuation.resume(emptyList()) { cause -> }
                }
            )
    }

    // 读取特征值（协程版本）
    suspend fun readCharacteristic(characteristicUuid: String): ByteArray? = suspendCancellableCoroutine { continuation ->
        val connection = connection ?: run {
            _connectionErrors.tryEmit("未连接到设备")
            continuation.resume(null) { cause -> }
            return@suspendCancellableCoroutine
        }

        connection.readCharacteristic(UUID.fromString(characteristicUuid))
            .subscribe(
                { data ->
                    continuation.resume(data) { cause -> }
                },
                { throwable ->
                    _connectionErrors.tryEmit("读取失败: ${throwable.message}")
                    continuation.resume(null) { cause -> }
                }
            )
    }

    // 写入特征值（协程版本）
    suspend fun writeCharacteristic(characteristicUuid: String, data: ByteArray): Boolean = suspendCancellableCoroutine { continuation ->
        val connection = connection ?: run {
            _connectionErrors.tryEmit("未连接到设备")
            continuation.resume(false) { cause -> }
            return@suspendCancellableCoroutine
        }

        connection.writeCharacteristic(UUID.fromString(characteristicUuid), data)
            .subscribe(
                {
                    continuation.resume(true) { cause -> }
                },
                { throwable ->
                    _connectionErrors.tryEmit("写入失败: ${throwable.message}")
                    continuation.resume(false) { cause -> }
                }
            )
    }

    // 订阅特征值变化
    fun subscribeToCharacteristic(characteristicUuid: String): Disposable? {
        val connection = connection ?: run {
            _connectionErrors.tryEmit("未连接到设备")
            return null
        }

        return connection.setupNotification(UUID.fromString(characteristicUuid))
            .flatMap { it } // 获取通知流
            .subscribe(
                { data ->
                    _characteristicChanges.tryEmit(Pair(characteristicUuid, data))
                },
                { throwable ->
                    _connectionErrors.tryEmit("订阅失败: ${throwable.message}")
                }
            )
    }

    // 释放资源
    fun cleanup() {
        disconnect()
        stopScan()
    }
}