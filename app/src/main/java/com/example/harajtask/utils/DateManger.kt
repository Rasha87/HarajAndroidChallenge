package com.example.harajtask.utils

import java.text.DateFormat

import java.util.*

object DateManger
{
     fun getTime(time: Long): String {
        val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
        cal.setTimeInMillis(time * 1000)
        return android.text.format.DateFormat.format("H",cal).toString()

    }
    fun getDate(time: Long): String {
        val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
        cal.setTimeInMillis(time * 1000)
        return android.text.format.DateFormat.format("yyyy/MM/dd HH:MM aa",cal).toString()

    }
}