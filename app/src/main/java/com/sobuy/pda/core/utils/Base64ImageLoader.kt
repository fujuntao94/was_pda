package com.sobuy.pda.core.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import androidx.annotation.WorkerThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Base64ImageLoader {

    // 带协程处理的异步加载方法
    fun loadImageAsync(imageView: ImageView, base64String: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val bitmap = withContext(Dispatchers.IO) {
                decodeBase64ToBitmap(base64String)
            }
            bitmap?.let {
                imageView.setImageBitmap(it)
            }
        }
    }

    // 同步解码方法（建议在后台线程调用）
    @WorkerThread
    fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val pureBase64Encoded = base64String.substringAfter(",", "")
            val decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}