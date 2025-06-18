package com.sobuy.pda.api

data class UserInfo(
    val login: String,
    val id: Long,
    val avatar_url: String,
    val name: String?,
    val bio: String?,
    val code: Int
)