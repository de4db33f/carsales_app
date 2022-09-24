package com.aplication.carsales.common.utils

import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {
    fun getHour(epoch: Long): String = getFormattedTime(epoch, "HH:mm")

    fun getFullDate(epoch: Long): String = getFormattedTime(epoch, "yyyy-MM-dd")

    private fun getFormattedTime(epoch: Long, pattern: String): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(epoch)
    }

}