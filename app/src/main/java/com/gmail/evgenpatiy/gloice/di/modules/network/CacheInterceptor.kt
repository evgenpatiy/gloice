package com.gmail.evgenpatiy.gloice.di.modules.network

import com.gmail.evgenpatiy.gloice.helpers.validator.GloiceValidator
import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor(private val validator: GloiceValidator) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val maxAge = 60 // 1 minute old
        val maxCacheAge: Int = 60 * 60 * 24 * 7 // cache max age - 7 days

        val request = if (validator.isNetworkConnected()) {
            chain
                .request()
                .newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        } else {
            chain
                .request()
                .newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxCacheAge")
                .build()
        }
        return chain.proceed(request)
    }
}