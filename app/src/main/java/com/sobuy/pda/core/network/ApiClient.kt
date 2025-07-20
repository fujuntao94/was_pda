package com.sobuy.pda.core.network

import com.google.gson.GsonBuilder
import com.sobuy.pda.core.config.Config
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = Config.API_URL
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L

    // 应用拦截器 - 用于添加公共参数、请求头
    private val appInterceptor = Interceptor { chain ->
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            // 添加 token 等认证信息
//            .header("Authorization", "Bearer your_token_here")

        val request = requestBuilder.build()
        chain.proceed(request)
    }

    // 日志拦截器
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // OkHttp 客户端
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(appInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    // Gson 配置
    private val gson = GsonBuilder()
        .create()

    // Retrofit 实例
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}