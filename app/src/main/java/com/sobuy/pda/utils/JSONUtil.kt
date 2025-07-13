package com.sobuy.pda.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object JSONUtil {
    fun createGson(): Gson {
        val gsonBuilder = GsonBuilder();

        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        return gsonBuilder.create()
    }

    fun toJson(data: Any): String {
        return createGson().toJson(data)
    }

    fun <T> fromJson(data: String, clazz: Class<T>): T {
        return createGson().fromJson(data, clazz)
    }


}