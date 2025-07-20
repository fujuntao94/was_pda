package com.sobuy.pda.feature.login.data.model

data class CaptchaImageResponse(
    var code: Int = 0,
    var message: String? = null,
    var img: String? = null,
    var uuid: String = "",
    var captchaOnOff: Boolean = false
)