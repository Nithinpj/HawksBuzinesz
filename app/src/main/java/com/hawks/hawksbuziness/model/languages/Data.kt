package com.hawks.hawksbuziness.model.languages

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "language")
data class Data(
    @PrimaryKey
    val id: Int,
    val language: String,
    val status: Int,
    val updated: String
)