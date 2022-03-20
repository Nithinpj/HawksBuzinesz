package com.hawks.hawksbuziness.model.places

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "place")
data class Data(
    val country_id: Int?,
    val district_id: Int?,
    @PrimaryKey
    val id: Int?,
    val place: String?,
    val state_id: Int?,
    val status: Int?,
    val updated: String?
){
    override fun toString(): String {
       return place.toString()
    }
}