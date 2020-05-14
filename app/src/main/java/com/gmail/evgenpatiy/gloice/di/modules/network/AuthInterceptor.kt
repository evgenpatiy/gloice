package com.gmail.evgenpatiy.gloice.di.modules.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val tmdbApiKey = "666d0231d1e745a73f00c4af766fd42b"

        val authUrl = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", tmdbApiKey)
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(authUrl)
            .build()
        return chain.proceed(request)
    }
}