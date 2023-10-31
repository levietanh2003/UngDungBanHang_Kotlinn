package com.example.ungdungbanhang.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ungdungbanhang.R
import com.example.ungdungbanhang.databinding.ActivityShopingBinding

class ShoppingActivity : AppCompatActivity() {
    /*val binding by lazy {
        ActivityShopingBinding.inflate(layoutInflater)
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping)

        /*val navController = findNavController(R.id.shopingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)*/
    }
}