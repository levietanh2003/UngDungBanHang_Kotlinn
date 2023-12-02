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
/*
* enum class OrderStatus(val status: String) {
    Ordered("Đã đặt hàng"),
    Canceled("Hủy"),
    Confirmed("Đã xác nhận"),
    Shipped("Đã vận chuyển"),
    Delivered("Đã giao hàng"),
    Returned("Đã trả hàng")
}

// Lấy trạng thái đơn hàng hiện tại
// Lấy trạng thái đơn hàng hiện tại
fun getOrderStatus(status: String): OrderStatus =
    OrderStatus.values().find { it.status == status } ?: run {
        // Xử lý trường hợp không khớp ở đây, có thể log hoặc thông báo lỗi
        OrderStatus.Ordered
    }
*
* */