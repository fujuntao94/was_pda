package com.sobuy.pda.core.config

import com.sobuy.pda.BuildConfig

object Config {
    val DEBUG: Boolean = BuildConfig.DEBUG
    
    const val API_URL = BuildConfig.API_URL

    const val NETWORK_CACHE_SIZE = (1024 * 1024 * 1000).toLong()
}