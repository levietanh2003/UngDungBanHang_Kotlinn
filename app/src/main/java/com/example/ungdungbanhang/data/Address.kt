package com.example.ungdungbanhang.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(
    val addressTitle: String,
    val fullName: String,
    val phoneNumber: String,
    val street: String,
    val city: String,
    val state: String
): Parcelable {
    constructor(): this("","","","","","")
}
