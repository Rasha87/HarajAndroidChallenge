package com.example.harajtask.data

import java.io.Serializable
import java.util.*

data class HarajData( val title: String? , val username: String ?, val thumbURL: String?, val commentCount: Int?,
val city:String? , val date: Long? , val body :String?
) : Serializable