package com.example.ungdungbanhang.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ungdungbanhang.R
import com.example.ungdungbanhang.databinding.ActivityShopingBinding
<<<<<<< HEAD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
=======

>>>>>>> myremote/main
class ShoppingActivity : AppCompatActivity() {
    /*val binding by lazy {
        ActivityShopingBinding.inflate(layoutInflater)
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping)

<<<<<<< HEAD
        //val navController = findNavController(R.id.shoppingHostFragment)
        //binding.bottomNavigation.setupWithNavController(navController)
    }
}
=======
        /*val navController = findNavController(R.id.shopingHostFragment)
        binding.bottomNavigation.setupWithNavController(navController)*/
    }
}
>>>>>>> myremote/main
