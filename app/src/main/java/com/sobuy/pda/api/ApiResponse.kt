package com.sobuy.pda.api

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val exception: Throwable) : ApiResponse<Nothing>()
    data class Failure(val code: Int, val message: String?) : ApiResponse<Nothing>()
}    