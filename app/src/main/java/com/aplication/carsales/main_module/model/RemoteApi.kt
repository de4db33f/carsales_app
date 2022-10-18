package com.aplication.carsales.main_module.model

import com.aplication.carsales.common.data_access.CovidHeaderInterceptor
import com.aplication.carsales.common.data_access.CovidService
import com.aplication.carsales.common.entities.CovidDataEntity
import com.aplication.carsales.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteApi {

    private val client = OkHttpClient.Builder().addInterceptor(CovidHeaderInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CovidService::class.java)

    suspend fun getCovidDataFromDate(date: String): CovidDataEntity = withContext(Dispatchers.IO) {
        service.getCovidDataFromDate(date)
    }
}