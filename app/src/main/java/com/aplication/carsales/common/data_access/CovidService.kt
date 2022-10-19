package com.aplication.carsales.common.data_access

import com.aplication.carsales.common.entities.CovidDataEntity
import com.aplication.carsales.common.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService {

    @GET(Constants.API_PATH)
    suspend fun getCovidDataFromDate(
        @Query(Constants.DATE_PARAM) date: String,
    ): CovidDataEntity
}