package com.sobuy.pda.api

import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {
    // 示例API：获取用户信息
    @GET("captchaImage")
    suspend fun getUserInfo(): ApiResponse<UserInfo>
}    