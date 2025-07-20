package com.sobuy.pda.core.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BleViewModel(application: Application) : AndroidViewModel(application) {
    private val bleManager = BleManager.getInstance(application.applicationContext)

    val connectionState: StateFlow<BleConnectionState> = bleManager.connectionState
    val scannedDevices: StateFlow<List<BleDevice>> = bleManager.scannedDevices
    val connectionErrors: SharedFlow<String> = bleManager.connectionErrors
    val characteristicChanges: SharedFlow<Pair<String, ByteArray>> = bleManager.characteristicChanges

    private var notificationDisposable: Disposable? = null

    // 开始扫描
    fun startScan() {
        bleManager.startScan()
    }

    // 停止扫描
    fun stopScan() {
        bleManager.stopScan()
    }

    // 连接设备
    fun connect(deviceAddress: String) {
        viewModelScope.launch {
            val success = bleManager.connect(deviceAddress)
            if (success) {
                discoverServices()
            }
        }
    }

    // 断开连接
    fun disconnect() {
        bleManager.disconnect()
        notificationDisposable?.dispose()
    }

    // 发现服务
    private suspend fun discoverServices() {
        val services = bleManager.discoverServices()
        // 处理发现的服务
    }

    // 读取特征值
    fun readCharacteristic(characteristicUuid: String) {
        viewModelScope.launch {
            val data = bleManager.readCharacteristic(characteristicUuid)
            if (data != null) {
                // 处理读取的数据
            }
        }
    }

    // 写入特征值
    fun writeCharacteristic(characteristicUuid: String, data: String) {
        viewModelScope.launch {
            val success = bleManager.writeCharacteristic(characteristicUuid, data.toByteArray())
            if (success) {
                // 写入成功
            }
        }
    }

    // 订阅特征值变化
    fun subscribeToCharacteristic(characteristicUuid: String) {
        notificationDisposable = bleManager.subscribeToCharacteristic(characteristicUuid)
    }

    override fun onCleared() {
        super.onCleared()
        bleManager.cleanup()
        notificationDisposable?.dispose()
    }
}