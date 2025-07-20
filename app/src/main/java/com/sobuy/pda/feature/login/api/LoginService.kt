package com.sobuy.pda.feature.login.api

import com.sobuy.pda.feature.login.data.model.PublicKeyResponse
import com.sobuy.pda.feature.login.data.model.CaptchaImageResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginService {
    @GET("captchaImage")
    suspend fun captchaImageApi(
    ): CaptchaImageResponse

    @GET("publicKey")
    suspend fun publicKeyApi(): Response<PublicKeyResponse>
}