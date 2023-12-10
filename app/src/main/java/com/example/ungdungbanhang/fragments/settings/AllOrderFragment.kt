package com.example.ungdungbanhang.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ungdungbanhang.adapters.AllOrdersAdapter
import com.example.ungdungbanhang.databinding.FragmentOrdersBinding
import com.example.ungdungbanhang.util.Resource
import com.example.ungdungbanhang.viewmodel.AllOrdersViewModel
import com.example.ungdungbanhang.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllOrderFragment: Fragment() {
    private lateinit var binding: FragmentOrdersBinding
    val viewModel by viewModels<AllOrdersViewModel>()
    val ordersAdapter by lazy { AllOrdersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOrdersRv()

        // luong xu ly load don hang len giao dien
        lifecycleScope.launchWhenStarted {
            // thuc hien hanh dong xu ly Live Data
            viewModel.allOrders.collect {
                when(it){
                    is Resource.Loading -> {
                        binding.progressbarAllOrders.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressbarAllOrders.visibility = View.GONE
                        ordersAdapter.differ.submitList(it.data)
                        if(it.data.isNullOrEmpty()){
                            binding.tvEmptyOrders.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), "Không có đơn hàng nào!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        binding.progressbarAllOrders.visibility = View.GONE
                    }
                    else -> Unit
                }
            }
        }
    }

    // cai dat recyclerview
    private fun setUpOrdersRv() {
        binding.rvAllOrders.apply {
            adapter = ordersAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }
}