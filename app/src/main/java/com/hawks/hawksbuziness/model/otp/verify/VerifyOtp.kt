package com.hawks.hawksbuziness.model.otp.verify

data class VerifyOtp(
    val `data`: Data,
    val message: String,
    val status: Int
)