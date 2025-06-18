package com.sobuy.pda.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

object NetworkUtils {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                ApiResponse.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        ApiResponse.Failure(throwable.code(), throwable.message())
                    }
                    is IOException -> {
                        ApiResponse.Error(throwable)
                    }
                    else -> {
                        ApiResponse.Error(throwable)
                    }
                }
            }
        }
    }
}