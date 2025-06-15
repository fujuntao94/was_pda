package com.sobuy.pda.api

import retrofit2.http.Query
import retrofit2.http.GET

interface DefaultNetworkService {

    @GET("/api/logo")
    suspend fun contents(
        @Query(value = "page") last: String?

    ): ContentWrapper

    companion object {
        fun create(): DefaultNetworkService {
            return NetworkModule.provideRetrofit(NetworkModule.provideOkHttpClient()).create(
                DefaultNetworkService::class.java
            )
        }
    }
}