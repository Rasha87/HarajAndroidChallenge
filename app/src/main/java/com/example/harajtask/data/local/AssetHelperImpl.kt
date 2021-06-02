package com.example.harajtask.data.local

import com.example.harajtask.data.HarajData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AssetHelperImpl : AssetHelper {

    override suspend fun getHarajData(jsonFileString: String?): List<HarajData> {
        val gson = Gson()
        val listHarajDataType = object : TypeToken<List<HarajData>>() {}.type
       return gson.fromJson(jsonFileString, listHarajDataType)
    }
}