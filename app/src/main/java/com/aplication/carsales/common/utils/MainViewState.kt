package com.aplication.carsales.common.utils

import com.aplication.carsales.common.entities.CovidDataEntity

sealed class MainViewState{
    object Loading: MainViewState()
    class Failure(val msg: Int): MainViewState()
    class  Success(val result: CovidDataEntity, val date: String): MainViewState()
}
