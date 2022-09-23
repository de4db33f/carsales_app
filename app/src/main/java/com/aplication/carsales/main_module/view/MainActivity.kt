package com.aplication.carsales.main_module.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.aplication.carsales.R
import com.aplication.carsales.BR
import com.aplication.carsales.databinding.ActivityMainBinding
import com.aplication.carsales.main_module.view_model.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.getSnackBarMsg().observe(this){ resMsg ->
                Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }

            it.getResult().observe(this){ result ->

            }
        }
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch{
            binding.viewModel.getCovidDataFromDate("2022-09-22")
        }
    }
}