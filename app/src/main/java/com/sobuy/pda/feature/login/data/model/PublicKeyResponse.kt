package com.sobuy.pda.feature.login.data.model

data class PublicKeyResponse(
    var code: Int = 0,
    var msg: String? = "",
    var data: PublicKey,
)

data class PublicKey(
    var publicKey: String,
)