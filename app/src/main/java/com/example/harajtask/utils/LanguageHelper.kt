package com.example.harajtask.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import java.util.*

object LanguageHelper
{
    fun arabicLanguage(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            (context as Activity).window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }

    fun getLocale(): Locale? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Locale.getDefault(Locale.Category.FORMAT)
        } else {
            Locale.getDefault()
        }
    }
}