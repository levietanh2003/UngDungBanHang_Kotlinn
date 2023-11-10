package com.example.ungdungbanhang.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ungdungbanhang.data.Category
import com.example.ungdungbanhang.data.Product
import com.example.ungdungbanhang.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategorViewMod constructor(
    private val firestore: FirebaseFirestore,
    private val category: Category

): ViewModel() {

    private val _offerProduct = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val offerProduct = _offerProduct.asStateFlow()

    private val _bestProduct = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val bestProduct = _bestProduct.asStateFlow()

    init {
        fetchOfferProduct()
        fetchBestProduct()
    }

    // phieu san pham co Offer tot
    fun fetchOfferProduct()
    {
        viewModelScope.launch {
            _offerProduct.emit(Resource.Loading())

        }

        // truy van
        firestore.collection("Product").whereEqualTo("category",category.category)
            .whereNotEqualTo("offerPercentage",null).get()
            .addOnSuccessListener {
                val products = it.toObjects(Product::class.java)
                viewModelScope.launch {
                    _offerProduct.emit(Resource.Success(products))
                }

            }.addOnFailureListener {
                viewModelScope.launch {
                    _offerProduct.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    // fill san pham tot theo danh muc
    fun fetchBestProduct(){
        viewModelScope.launch {
            _bestProduct.emit(Resource.Loading())
        }

        // truy van
        firestore.collection("Product").whereEqualTo("category",category.category)
            .whereNotEqualTo("offerPercentage",null).get()
            .addOnSuccessListener {
                val products = it.toObjects(Product::class.java)
                viewModelScope.launch{
                    _bestProduct.emit(Resource.Success(products))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _offerProduct.emit(Resource.Error(it.message.toString()))
                }
            }
    }
}