package com.example.ungdungbanhang.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ungdungbanhang.R
import com.example.ungdungbanhang.adapters.BestProudctApdapter
import com.example.ungdungbanhang.databinding.FragmentBaseCategoryBinding

open class BaseCatogoryFragment: Fragment(R.layout.fragment_base_category) {
    private lateinit var binding: FragmentBaseCategoryBinding
    private val offerApdater: BestProudctApdapter by lazy { BestProudctApdapter() }
    private val bestProudctApdapter: BestProudctApdapter by lazy { BestProudctApdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTopOfferRv()
        seUpBestProductRv()
    }

    fun hideBestProductLoangding()
    {
        binding.bestProductsProgressBar.visibility = View.GONE
    }

    fun showBestProductLoading(){
        binding.bestProductsProgressBar.visibility = View.VISIBLE
    }
    private fun seUpBestProductRv() {
        binding.rvBestProducts.apply {
            layoutManager =
                GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
            adapter = bestProudctApdapter
        }
    }

    private fun setUpTopOfferRv() {
        binding.rvOfferProducts.apply {
            layoutManager =
                LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            adapter = offerApdater
        }
    }

}