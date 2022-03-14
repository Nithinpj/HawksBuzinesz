package com.hawks.hawksbuziness.model.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Data(
    val head_id: Int,
    val id: Int,
    val key: String,
    val meta_id: Int,
    val status: Int,
    val updated: String,
    val value: String,

    @PrimaryKey(autoGenerate = true)
    val primaryId:Int
)
{
    override fun toString(): String {
        return key
    }
}