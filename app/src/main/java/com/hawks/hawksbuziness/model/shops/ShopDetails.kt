package com.hawks.hawksbuziness.model.shops

import com.hawks.hawksbuziness.model.shop.Shop

data class ShopDetails(
    val message: String,
    val shop: List<Shop>,
    val shop_id: String,
    val status: Int,
    val user_id: String
)