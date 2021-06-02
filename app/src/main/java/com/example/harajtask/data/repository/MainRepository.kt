package com.mindorks.framework.mvvm.data.repository

import com.example.harajtask.data.HarajData
import com.example.harajtask.data.local.AssetHelper

class MainRepository(private val assetHelper: AssetHelper) {

    suspend fun getHarajData(jsonFileData:String?): List<HarajData> {
        return assetHelper.getHarajData(jsonFileData)
    }

}