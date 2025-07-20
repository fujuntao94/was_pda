package com.sobuy.pda.feature.login.data.model

data class LoginResponse(
    var token: String? = "",
    var code: Int = 0,
    var msg: String? = ""
)