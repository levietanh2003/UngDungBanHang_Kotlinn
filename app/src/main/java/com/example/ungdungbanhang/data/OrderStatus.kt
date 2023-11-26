package com.example.ungdungbanhang.data

sealed class OrderStatus(val status: String){
    //object Success: OrderStatus("Thành công")
    //object Failed: OrderStatus("Thất bại")
    //object Unspecified: OrderStatus("Chưa xác định")
    object Ordered: OrderStatus("Đã đặt hàng")
    object Canceled: OrderStatus("Hủy")
    object Confirmed: OrderStatus("Đã xác nhận")
    object Shipped: OrderStatus("Đã vận chuyển")
    object Delivered: OrderStatus("Delivered")
    object Returned: OrderStatus("Đã trả hàng")
}
