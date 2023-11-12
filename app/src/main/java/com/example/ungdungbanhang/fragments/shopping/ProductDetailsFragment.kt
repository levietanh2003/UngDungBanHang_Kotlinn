package com.example.ungdungbanhang.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ungdungbanhang.adapters.ColorsAdapter
import com.example.ungdungbanhang.adapters.SizesAdapter
import com.example.ungdungbanhang.adapters.ViewPager2Images
import com.example.ungdungbanhang.databinding.FragmentProductDetailsBinding
import com.example.ungdungbanhang.helper.formatPriceVN
import com.example.ungdungbanhang.util.hideBottomNavigation

class ProductDetailsFragment: Fragment() {
    private val args by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { ViewPager2Images() }
    private val sizeApdater by lazy { SizesAdapter() }
    private val colorAdapter by lazy { ColorsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // an bottom navigation
        hideBottomNavigation()
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product

        setUpSizesRv()
        seUpColorsRv()
        sepUpViewPager()

        // su kien close
        binding.imageClose.setOnClickListener {
            requireActivity().onBackPressed()
            //findNavController().navigateUp()
        }

        // do du lieu thong tin san pham
        binding.apply {
            tvProductName.text = product.name
            tvProductPrice.text = formatPriceVN(product.price.toDouble())
            tvProductDescription.text = product.description

            // neu san pham khong co mau hoac size an di
            if(product.colors.isNullOrEmpty()){
                tvProductColors.visibility = View.INVISIBLE
            }
            if(product.sizes.isNullOrEmpty()){
                tvProductSize.visibility = View.INVISIBLE
            }
        }
        viewPagerAdapter.differ.submitList(product.images)
        product.colors?.let {colorAdapter.differ.submitList(it)}
        product.sizes?.let {sizeApdater.differ.submitList(it)}
    }

    private fun sepUpViewPager() {
        binding.apply {
            viewPagerProductImages.adapter = viewPagerAdapter
        }
    }

    private fun seUpColorsRv() {
        binding.rvColors.apply {
            adapter = colorAdapter
            layoutManager =
                LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun setUpSizesRv() {
        binding.rvSizes.apply {
            adapter = sizeApdater
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
    }
}