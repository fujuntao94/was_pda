package com.sobuy.pda.feature.login.data.repository

import com.sobuy.pda.core.network.ApiClient
import com.sobuy.pda.core.network.Resource
import com.sobuy.pda.feature.login.api.LoginService
import com.sobuy.pda.feature.login.data.model.CaptchaImageResponse
import com.sobuy.pda.feature.login.data.model.PublicKeyResponse

object LoginRepository {
    private val service: LoginService by lazy {
        ApiClient.retrofit.create(LoginService::class.java)
    }

    suspend fun captchaImageApi(): CaptchaImageResponse {
        return service.captchaImageApi()
    }

    suspend fun publicKeyApi(): Resource<PublicKeyResponse> {
        return try {
            val response = service.publicKeyApi()
            if (response.isSuccessful) {
                response.body()?.let {
                    return if (it.code == 200) {
                        Resource.Success(it)
                    } else {
                        Resource.Error(it.msg!!)
                    }
                }
                Resource.Error("无响应数据")
            } else {
                Resource.Error("${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("${e.message}")
        }
    }

}