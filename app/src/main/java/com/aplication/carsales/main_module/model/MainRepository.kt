package com.aplication.carsales.main_module.model

import com.aplication.carsales.common.entities.CovidDataEntity

class MainRepository {
    private val remoteApi = RemoteApi()

    suspend fun getWeatherForecast(date: String) : CovidDataEntity =
        remoteApi.getCovidDataFromDate(date)
}