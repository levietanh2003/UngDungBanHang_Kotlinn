package com.example.ungdungbanhang.data.order

sealed class OrderStatus(val status: String){
    object Ordered: OrderStatus("Đã đặt hàng")
    object Canceled: OrderStatus("Hủy")
    object Confirmed: OrderStatus("Đã xác nhận")
    object Shipped: OrderStatus("Đã vận chuyển")
    object Delivered: OrderStatus("Đã giao hàng")
    object Returned: OrderStatus("Đã trả hàng")
}

// lay ra trang thai don hang hien tai
fun getOrderStatus(status: String): OrderStatus{
    return when (status) {
        "Đã đặt hàng" -> OrderStatus.Ordered
        "Hủy" -> OrderStatus.Canceled
        "Đã xác nhận" -> OrderStatus.Confirmed
        "Đã vận chuyển" -> OrderStatus.Shipped
        "Đã giao hàng" -> OrderStatus.Delivered
        "Đã trả hàng" -> OrderStatus.Returned
        else -> OrderStatus.Ordered
    }
}