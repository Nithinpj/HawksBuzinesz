package com.nithi.hawksbuziness.model.country

data class Data(
    val country: String,
    val created: String,
    val delete_status: Int,
    val deleted_at: Any,
    val deleted_by: Any,
    val id: Int,
    val status: Int,
    val updated: String
){
    override fun toString(): String {
        return country.toString()
    }
}
