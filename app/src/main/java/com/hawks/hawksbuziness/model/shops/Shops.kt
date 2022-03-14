package com.hawks.hawksbuziness.model.shops

data class Shops(
    val message: String,
    val shops: List<Shop>,
    val status: Int,
    val user_id: String
)