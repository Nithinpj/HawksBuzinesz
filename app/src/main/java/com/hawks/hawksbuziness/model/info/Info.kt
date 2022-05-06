package com.hawks.hawksbuziness.model.info
data class Info(
    val `data`: Data,
    val message: String,
    val status: Int
)

data class Data(
    val delete_status: Int,
    val email: String,
    val id: Int,
    val mobile: String,
    val privacy: String,
    val terms: String,
    val updated: String,
    val version: String,
    val whatsapp: String
)