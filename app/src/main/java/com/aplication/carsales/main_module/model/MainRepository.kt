package com.aplication.carsales.main_module.model

import com.aplication.carsales.common.entities.CovidDataEntity

class MainRepository {
    private val remoteApi = RemoteApi()

    suspend fun getCovidDataFromDate(date: String) : CovidDataEntity =
        remoteApi.getCovidDataFromDate(date)
}