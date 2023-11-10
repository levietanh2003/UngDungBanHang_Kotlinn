package com.example.ungdungbanhang.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ungdungbanhang.data.Category
import com.example.ungdungbanhang.util.Resource
import com.example.ungdungbanhang.viewmodel.CategoyViewModel
import com.example.ungdungbanhang.viewmodel.factory.BaseCategoryViewModelsFactoryFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class TableFragment: BaseCatogoryFragment() {
    @Inject
    lateinit var firestore: FirebaseFirestore
    val viewModel by viewModels<CategoyViewModel> {
        BaseCategoryViewModelsFactoryFactory(firestore, Category.Table)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.offerProduct.collectLatest {
                when(it){
                    is Resource.Loading ->{
                        showOfferLoading()
                    }
                    is Resource.Success -> {
                        offerApdater.differ.submitList(it.data)
                        hideOfferLoading()
                    }
                    is  Resource.Error -> {
                        Snackbar.make(requireView(),it.message.toString(), Snackbar.LENGTH_LONG)
                            .show()
                        hideOfferLoading()
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.bestProduct.collectLatest {
                when(it){
                    is Resource.Loading -> {
                        showBestProductLoading()
                    }
                    is  Resource.Success -> {
                        bestProudctApdapter.differ.submitList(it.data)
                        hideOfferLoading()
                    }
                    is Resource.Error -> {
                        Snackbar.make(requireView(),it.message.toString(), Snackbar.LENGTH_LONG)
                            .show()
                        hideOfferLoading()
                    }
                }
            }
        }
    }
    override fun onBestProductsPagingRequest() {

    }

    override fun onOfferPagingRequest() {

    }
}