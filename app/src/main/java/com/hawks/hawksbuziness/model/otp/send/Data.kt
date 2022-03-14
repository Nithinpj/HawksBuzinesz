package com.hawks.hawksbuziness.model.otp.send

data class Data(
    val created: String,
    val id: Int,
    val otp: Int,
    val user_id: Int
)