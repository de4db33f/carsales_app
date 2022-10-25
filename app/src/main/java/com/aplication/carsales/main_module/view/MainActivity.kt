package com.aplication.carsales.main_module.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aplication.carsales.R
import com.example.routercovidmodule.RouterModule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, RouterModule().getCovidModuleFragment()).commitNow()
    }

}