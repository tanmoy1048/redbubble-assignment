package com.redbubble.redbubblehomework.ui.detail.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.redbubble.redbubblehomework.databinding.DetailFragmentBinding
import com.redbubble.redbubblehomework.databinding.ProductDetailFragmentBinding
import com.redbubble.redbubblehomework.ui.detail.DetailFragmentArgs
import com.redbubble.redbubblehomework.ui.main.HomeAdapter
import com.redbubble.redbubblehomework.ui.main.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment() {
    lateinit var viewModel: ProductDetailFragmentViewModel

    @Inject
    lateinit var homeAdapter: HomeAdapter

    @Inject
    lateinit var featureAdapter: FeatureSetAdapter

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(ProductDetailFragmentViewModel::class.java)
        return ProductDetailFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            adapter = homeAdapter
            featureAdapter = featureAdapter
            glide = requestManager
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()
        viewModel.fetchItemDetail()
    }

    private fun subscribeUi() {
        viewModel.workDetail.observe(viewLifecycleOwner, { product ->
            product?.let {
                homeAdapter.updateData(it.availableProducts)
                it.featureSet?.let { feature -> featureAdapter.updateData(feature.features) }
            }
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->
            error?.let { showSnackBar(error) }
        })
    }
}


