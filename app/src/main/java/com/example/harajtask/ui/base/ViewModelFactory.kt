package com.example.harajtask.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harajtask.data.local.AssetHelper
import com.example.harajtask.ui.main.viewmodel.MainViewModel
import com.mindorks.framework.mvvm.data.repository.MainRepository


class ViewModelFactory(private val jsonFileString : String?,private val assetHelper: AssetHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(jsonFileString,MainRepository(assetHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}