package com.sobuy.pda.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sobuy.pda.AppContext
import com.sobuy.pda.config.Config
import com.sobuy.pda.utils.JSONUtil
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    fun provideOkHttpClient(): OkHttpClient {
        val okhttpClientBuilder = OkHttpClient.Builder()

        val cache = Cache(AppContext.instance.cacheDir, Config.NETWORK_CACHE_SIZE)

        okhttpClientBuilder.cache(cache)

        okhttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(
                10,
                TimeUnit.SECONDS
            )

//        okhttpClientBuilder.addInterceptor(TokenInterceptor())

        if (Config.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()

            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            okhttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okhttpClientBuilder.addInterceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build()
            chain.proceed(newRequest)
        }
        return okhttpClientBuilder.build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .create()
        return Retrofit.Builder().baseUrl(Config.API_URL).client(okHttpClient).addConverterFactory(
            GsonConverterFactory.create(gson)
        ).build()
    }
}