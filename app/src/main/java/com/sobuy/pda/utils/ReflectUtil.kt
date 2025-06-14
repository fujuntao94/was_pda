package com.sobuy.pda.utils

import android.view.LayoutInflater
import java.lang.reflect.ParameterizedType

object ReflectUtil {
    fun <VB> newViewBinding(layoutInflater: LayoutInflater, clazz: Class<*>): VB {
        return try {
            val type = try {
                clazz.genericSuperclass as ParameterizedType
            } catch (e: ClassCastException) {
                clazz.superclass.genericSuperclass as ParameterizedType
            }

            val clazzVB = type.actualTypeArguments[0] as Class<VB>

            val inflateMethod = clazzVB.getMethod("inflate", layoutInflater::class.java)
            inflateMethod.invoke(null, layoutInflater) as VB
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(e)
        }
    }
}