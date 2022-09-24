package com.aplication.carsales.main_module.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.aplication.carsales.BR
import com.aplication.carsales.R
import com.aplication.carsales.common.utils.CommonUtils
import com.aplication.carsales.databinding.ActivityMainBinding
import com.aplication.carsales.main_module.view_model.MainViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
        setupViews()

    }

    private fun setupViews() {
        setupTitle(null)
        binding.selectDate.setOnClickListener {
            val dpd = MaterialDatePicker.Builder.datePicker().build()
            dpd.addOnPositiveButtonClickListener {
                lifecycleScope.launch{
                    Log.i("DATEEEEE", CommonUtils.getFullDate(it))
                    binding.viewModel?.getCovidDataFromDate(CommonUtils.getFullDate(it))
                }
            }
            dpd.show(supportFragmentManager, "")
        }
    }

    private fun setupTitle(date: String?){
        val sdf = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        var currentDate = ""
        currentDate = if(date==null) {
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -1)
            sdf.format(cal.time)
        }else{
            sdf.format(date)
        }
        binding.date.text = currentDate.replace("-", " de ")
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.getSnackBarMsg().observe(this){ resMsg ->
                Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }

            it.getResult().observe(this){ result ->
                binding.confirmCases.text = result.data.confirmed.toString()
                binding.numDeaths.text = result.data.deaths.toString()
            }

            it.getdateSelected().observe(this){date ->
                setupTitle(date)
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
            binding.viewModel?.getCovidDataFromDate("2022-09-22")
        }
    }
}