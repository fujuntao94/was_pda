package com.sobuy.pda.core.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // 添加认证头
        val newRequest = originalRequest.newBuilder()
//            .header("Authorization", "Bearer ${AuthManager.token}")
            .build()

        return chain.proceed(newRequest)
    }
}