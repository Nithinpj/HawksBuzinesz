package com.nithi.hawksbuziness.model.profile

data class profile(
    val auth: Auth,
    val message: String,
    val status: Int,
    val user_id: String
)