package com.hawks.hawksbuziness.model.ticket

data class Data(
    val created: Any,
    val delete_status: Int,
    val deleted_at: Any,
    val deleted_by: Any,
    val id: Int,
    val message: String,
    val status: Int,
    val ticket_no: String,
    val title: String,
    val updated: String,
    val user_id: Int
)