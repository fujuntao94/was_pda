package com.sobuy.k.util

object StringUtil {
    fun isNickname(value: String): Boolean {
        return value.length in 2..10
    }
}