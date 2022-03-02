package com.nithi.hawksbuziness.model.profile

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "auth")
data class Auth(
    val auth_id: String?,
    val country: String?,
    val created: String?,
    val delete_status: Int?,
    val deleted_at: String?,
    val deleted_by: String?,
    val device_id: String?,
    val dob: String?,
    val email: String?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val join_method: String?,
    val mobile: String?,
    val name: String?,
    val nationality: String?,
    val pass: String?,
    val password: String?,
    val place: String?,
    val provider_key: String?,
    val provider_type: String?,
    val referer_id: String?,
    val referral_code: String?,
    val shop_id: String?,
    val status: Int?,
    val type: String?,
    val updated: String?,
    val user_id: String?,
    val username: String?,

    @PrimaryKey
    val primaryId:Int?

)