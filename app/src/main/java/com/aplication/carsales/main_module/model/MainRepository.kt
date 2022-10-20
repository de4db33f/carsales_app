package com.aplication.carsales.main_module.model

import com.aplication.carsales.common.entities.CovidDataEntity
import kotlinx.coroutines.flow.Flow

class MainRepository {

    fun getCovidDataFromDate(date: String): Flow<CovidDataEntity> =
        RemoteApi.getCovidDataFromDate(date)
}