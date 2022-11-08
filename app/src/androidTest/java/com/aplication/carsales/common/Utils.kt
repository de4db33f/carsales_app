package com.aplication.carsales.common

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getDateFormatted(date: String): String {
        return try {
            val formattedDateSplit = date.split("-")
            formattedDateSplit[2] + " de " + getMonthName(date) + " del " + formattedDateSplit[0]
        } catch (e: Exception) {
            ""
        }

    }

    private fun getMonthName(date: String): String {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat(Constants.DATE_PATTERN, Locale("es", "ES"))
        cal.time = sdf.parse(date) as Date
        return SimpleDateFormat("MMMM", Locale("es", "ES")).format(cal.time)
    }

    fun getDateFromToday(amountDaysBefore: Int):String{
        val dateFormat: DateFormat = SimpleDateFormat(Constants.DATE_PATTERN)
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DATE, amountDaysBefore)
        return dateFormat.format(cal.time)
    }

    fun getDateAsSeparatedInt(date: String): List<Int>{
        return date.split("-").map {
            it.toInt()
        }
    }
}