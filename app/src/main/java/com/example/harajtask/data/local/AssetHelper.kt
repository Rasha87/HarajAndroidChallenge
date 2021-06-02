package com.example.harajtask.data.local

import com.example.harajtask.data.HarajData

interface AssetHelper {
    suspend fun getHarajData(jsonFileString: String?): List<HarajData>

}