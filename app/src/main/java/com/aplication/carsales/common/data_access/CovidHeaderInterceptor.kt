package com.aplication.carsales.common.data_access

import okhttp3.Interceptor
import okhttp3.Response

class CovidHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-RapidAPI-Key", "96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e")
                .build()
        )
    }
}