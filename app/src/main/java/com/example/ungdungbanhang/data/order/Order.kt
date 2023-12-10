package com.example.ungdungbanhang.data.order

import com.example.ungdungbanhang.data.Address
import com.example.ungdungbanhang.data.CartProduct
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


data class Order (
        val orderStatus: String = "",
        val totalPrice: Float = 0f,
        val products: List<CartProduct> = emptyList(),
        val address: Address = Address(),
        // cho date là ngày hiện tại
        val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(Date()),
        // mã đơn hàng
        //val orderId: Long = nextLong(0,100_000_000_000) + totalPrice.toLong()
        val orderId: Long = (1..10).joinToString("") { Random.nextInt(0, 10).toString() }.toLong()
)