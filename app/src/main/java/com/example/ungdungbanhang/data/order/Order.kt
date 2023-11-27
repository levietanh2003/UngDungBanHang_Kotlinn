package com.example.ungdungbanhang.data.order

import com.example.ungdungbanhang.data.Address
import com.example.ungdungbanhang.data.CartProduct
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.random.Random.Default.nextLong


data class Order (
        val orderStatus: String,
        val totalPrice: Float,
        val products: List<CartProduct>,
        val address: Address,
        // cho date là ngày hiện tại
        val date: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
        // mã đơn hàng
        val orderId: Long = nextLong(0,100_000_000_000) + totalPrice.toLong()
        )