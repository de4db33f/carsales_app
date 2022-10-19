package com.aplication.carsales.main_module.model

import com.aplication.carsales.common.data_access.CovidHeaderInterceptor
import com.aplication.carsales.common.data_access.CovidService
import com.aplication.carsales.common.entities.CovidDataEntity
import com.aplication.carsales.common.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteApi {

    private val client = OkHttpClient.Builder().addInterceptor(CovidHeaderInterceptor()).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CovidService::class.java)

    fun getCovidDataFromDate(date: String): Flow<CovidDataEntity> = flow {
        emit(service.getCovidDataFromDate(date))
    }
}