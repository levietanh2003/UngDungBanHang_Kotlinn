package com.example.ungdungbanhang.data

sealed class Category(val category: String) {

    object Chair: Category("Ghế")
    object Shirt: Category("Áo")
    object Table: Category("Bàn")
    object Trouser: Category("Quần")
    object Shoe: Category("Giày")
    object Cupboard: Category("Tủ")
}