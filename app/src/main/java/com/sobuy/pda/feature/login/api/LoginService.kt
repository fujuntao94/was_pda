package com.sobuy.pda.feature.login.api

import com.sobuy.pda.feature.login.data.model.PublicKeyResponse
import com.sobuy.pda.feature.login.data.model.CaptchaImageResponse
import com.sobuy.pda.feature.login.data.model.FormRequest
import com.sobuy.pda.feature.login.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {
    @GET("captchaImage")
    suspend fun captchaImageApi(
    ): CaptchaImageResponse

    @GET("publicKey")
    suspend fun publicKeyApi(): Response<PublicKeyResponse>

    @POST("login")
    suspend fun loginApi(@Body request: FormRequest): Response<LoginResponse>
}