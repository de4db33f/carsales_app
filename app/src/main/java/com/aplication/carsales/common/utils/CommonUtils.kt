package com.aplication.carsales.common.utils

import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    fun getFullDate(epoch: Long): String = getFormattedTime(epoch, )

    private fun getFormattedTime(epoch: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val netDate = Date(epoch)
        return sdf.format(netDate)
    }

    fun getDateFormatted(date: String): String{
        return try {
            val formattedDateSplit = date.split("-")
            formattedDateSplit[0]+" de " + formattedDateSplit[1] + " del " + formattedDateSplit[2]
        }catch (e: Exception){
            ""
        }

    }

}