package com.aplication.carsales.main_module.view

import android.os.Bundle
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
import java.text.DateFormat
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
        binding.selectDateButton.setOnClickListener {
            val dpd = MaterialDatePicker.Builder.datePicker().build()
            dpd.addOnPositiveButtonClickListener {
                lifecycleScope.launch{
                    //FIXME: Le tuve que sumar 1 día (en milisegundos) porque siempre obtenía el epoch del día anterior al que seleccionaba
                    binding.viewModel?.getCovidDataFromDate(CommonUtils.getFullDate(it + 24 * 60 * 60 * 1000))
                }
            }
            dpd.show(supportFragmentManager, "DatePicker")
        }
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

            it.getDateSelected().observe(this){ date ->
                binding.date.text = CommonUtils.getDateFormatted(date)
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
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val cal = Calendar.getInstance()
            cal.add(Calendar.DATE, -2) //FIXME: el -2 es para restarle 2 días, ya que al momento de desarrollar (24/09) no habian datos para los días 24 y 23
            val current = dateFormat.format(cal.time)
            binding.viewModel?.getCovidDataFromDate(current)
        }
    }
}