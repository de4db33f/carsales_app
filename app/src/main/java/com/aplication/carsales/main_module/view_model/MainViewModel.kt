package com.aplication.carsales.main_module.view_model

import androidx.lifecycle.*
import com.aplication.carsales.R
import com.aplication.carsales.common.entities.CovidDataEntity
import com.aplication.carsales.main_module.usecases.GetCovidDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCovidDataUseCase: GetCovidDataUseCase
) : ViewModel() {

    private val result = MutableLiveData<CovidDataEntity>()
    fun getResult(): LiveData<CovidDataEntity> = result

    private val dateSelected = MutableLiveData<String>()
    fun getDateSelected(): LiveData<String> = dateSelected

    private val snackBarMsg = MutableLiveData<Int>()
    fun getSnackBarMsg() = snackBarMsg

    private val loading = MutableLiveData<Boolean>()
    fun isLoaded() = loading

    fun getCovidDataFromDate(date: String) {
        loading.postValue(false)
        getCovidDataUseCase(date).onEach {
            result.postValue(it)
            dateSelected.postValue(date.split("-").reversed().joinToString("-"))
            loading.postValue(true)
        }.catch {
            if (it.toString().contains("UnknownHostException")) {
                snackBarMsg.value = R.string.unknown_host_error
            } else {
                snackBarMsg.value = R.string.main_error
            }
            loading.postValue(true)
        }.launchIn(viewModelScope)
    }

}