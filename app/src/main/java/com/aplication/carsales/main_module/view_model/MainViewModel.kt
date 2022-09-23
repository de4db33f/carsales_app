package com.aplication.carsales.main_module.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aplication.carsales.R
import com.aplication.carsales.common.entities.CovidDataEntity
import com.aplication.carsales.main_module.model.MainRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class MainViewModel {
    private val repository = MainRepository()

    private val result = MutableLiveData<CovidDataEntity>()
    fun getResult(): LiveData<CovidDataEntity> = result

    private val snackBarMsg = MutableLiveData<Int>()
    fun getSnackBarMsg() = snackBarMsg

    private val loading = MutableLiveData<Boolean>()
    fun isLoaded() = loading

    suspend fun getCovidDataFromDate(date: String){
        viewModelScope.launch {
            try {
                loading.value = false
                val  resultServer = repository.getCovidDataFromDate(date)
                result.value = resultServer
            } catch (e: Exception) {
                snackBarMsg.value = R.string.main_error
            } finally {
                loading.value = true
            }
        }
    }
}