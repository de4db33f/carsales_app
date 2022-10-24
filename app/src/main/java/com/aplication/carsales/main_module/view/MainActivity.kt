package com.aplication.carsales.main_module.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.aplication.carsales.BR
import com.aplication.carsales.R
import com.aplication.carsales.common.utils.CommonUtils
import com.aplication.carsales.common.utils.MainViewState
import com.aplication.carsales.main_module.view_model.MainViewModel
import com.example.routercovidmodule.covidmodule.RouterApp
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RouterApp().getFragment()).commitNow()
    }

}