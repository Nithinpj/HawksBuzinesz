package com.hawks.hawksbuziness.model.shop

data class ShopDetails(
    val message: String,
    val shop: List<Shop>,
    val shop_id: String,
    val status: Int,
    val user_id: String
)