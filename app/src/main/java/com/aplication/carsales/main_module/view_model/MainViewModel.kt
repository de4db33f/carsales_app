package com.aplication.carsales.main_module.view_model

import androidx.lifecycle.*
import com.aplication.carsales.R
import com.aplication.carsales.common.entities.CovidDataEntity
import com.aplication.carsales.main_module.model.MainRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    private val result = MutableLiveData<CovidDataEntity>()
    fun getResult(): LiveData<CovidDataEntity> = result

    private val dateSelected = MutableLiveData<String>()
    fun getDateSelected(): LiveData<String> = dateSelected

    private val snackBarMsg = MutableLiveData<Int>()
    fun getSnackBarMsg() = snackBarMsg

    private val loading = MutableLiveData<Boolean>()
    fun isLoaded() = loading

    fun getCovidDataFromDate(date: String) {
        viewModelScope.launch {
            try {
                loading.value = false
                val resultServer = repository.getCovidDataFromDate(date)
                result.value = resultServer
                dateSelected.value = date.split("-").reversed().joinToString("-")
            } catch (e: UnknownHostException) {
                snackBarMsg.value = R.string.unknown_host_error
            } catch (e: Exception) {
                snackBarMsg.value = R.string.main_error
            } finally {
                loading.value = true
            }
        }
    }

}