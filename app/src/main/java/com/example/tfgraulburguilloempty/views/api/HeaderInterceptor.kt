package com.example.tfgraulburguilloempty.views.api

import okhttp3.Interceptor
import okhttp3.Response


class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("X-RapidAPI-Key", "eff920d561mshe24a265cad90ffcp182cecjsn2c386a312d4d")
            .header("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
            .build()
        return chain.proceed(modifiedRequest)
    }
}


