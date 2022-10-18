package com.aplication.carsales.main_module.usecases

import com.aplication.carsales.common.entities.CovidDataEntity
import com.aplication.carsales.main_module.model.MainRepository

class GetCovidDataUseCase(
    private val mainRepository: MainRepository
) {
    suspend fun execute(date: String): CovidDataEntity =
        mainRepository.getCovidDataFromDate(date)
}