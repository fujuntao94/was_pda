package com.sobuy.pda.api

import com.sobuy.pda.content.ContentWrapper
import retrofit2.http.Query
import retrofit2.http.GET

interface DefaultNetworkService {

    @GET("/captchaImage")
    suspend fun captchaImageApi(
    ): ContentWrapper

    companion object {
        fun create(): DefaultNetworkService {
            return NetworkModule.provideRetrofit(NetworkModule.provideOkHttpClient()).create(
                DefaultNetworkService::class.java
            )
        }
    }
}