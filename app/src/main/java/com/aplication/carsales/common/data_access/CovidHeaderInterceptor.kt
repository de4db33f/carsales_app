package com.aplication.carsales.common.data_access

import com.aplication.carsales.common.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class CovidHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader(Constants.HEADER_API_KEY, Constants.HEADER_API_VAL)
                .build()
        )
    }
}