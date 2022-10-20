package com.aplication.carsales.main_module.usecases

import com.aplication.carsales.common.entities.CovidDataEntity
import com.aplication.carsales.main_module.model.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCovidDataUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    operator fun invoke(date: String): Flow<CovidDataEntity> =
        mainRepository.getCovidDataFromDate(date).flowOn(context = Dispatchers.IO)
}