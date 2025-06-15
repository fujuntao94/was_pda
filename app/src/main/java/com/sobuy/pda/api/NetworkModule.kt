package com.sobuy.pda.api

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

        if (Config.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()

            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

            okhttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        return okhttpClientBuilder.build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(Config.API_URL).addConverterFactory(
            GsonConverterFactory.create(JSONUtil.createGson())
        ).build()
    }
}