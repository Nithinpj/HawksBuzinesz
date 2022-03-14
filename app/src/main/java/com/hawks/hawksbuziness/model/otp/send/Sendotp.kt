package com.hawks.hawksbuziness.model.otp.send

data class Sendotp(
    val `data`: Data,
    val message: String,
    val status: Int
)