package com.hawks.hawksbuziness.model.profile

data class UpdateProfile(
    val auth: AuthUpdate,
    val country: String,
    val email: String,
    val gender: String,
    val message: String,
    val mobile: String,
    val nationality: String,
    val place: String,
    val profile_image: String,
    val status: Int,
    val user_id: String
)