package com.nithi.hawksbuziness.model.shops

import java.io.Serializable

data class Shop(
    val auth_id: Int,
    val country: Any,
    val created: String,
    val delete_status: Int,
    val deleted_at: Any,
    val deleted_by: Any,
    val device_id: Any,
    val dob: Any,
    val email: String,
    val gender: Any,
    val id: Int,
    val image: Any,
    val join_method: Any,
    val mobile: String,
    val name: String,
    val nationality: Any,
    val pass: String,
    val password: String,
    val place: Any,
    val provider_key: Any,
    val provider_type: Any,
    val referer_id: Any,
    val referral_code: String,
    val shop_id: Int,
    val status: Int,
    val type: String,
    val updated: String,
    val user_id: Any,
    val username: String
):Serializable