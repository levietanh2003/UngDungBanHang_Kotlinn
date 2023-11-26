package com.example.ungdungbanhang.data

import java.util.Date

data class Order (
        val orderStatus: String,
        val totalPrice: Float,
        val products: List<CartProduct>,
        val address: Address,
        val dateOrder: Date
        )