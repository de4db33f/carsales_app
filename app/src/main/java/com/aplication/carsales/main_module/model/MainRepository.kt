package com.aplication.carsales.main_module.model

import com.aplication.carsales.common.entities.CovidDataEntity

class MainRepository {

    suspend fun getCovidDataFromDate(date: String) : CovidDataEntity =
        RemoteApi.getCovidDataFromDate(date)
}