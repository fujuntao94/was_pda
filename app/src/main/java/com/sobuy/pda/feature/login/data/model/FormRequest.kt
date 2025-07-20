package com.sobuy.pda.feature.login.data.model

data class FormRequest(
    val username: String,
    val password: String,
    val uuid: String,
    val language: String,
    val code: String,
)