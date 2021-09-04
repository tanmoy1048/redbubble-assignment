package com.redbubble.redbubblehomework.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.redbubble.redbubblehomework.databinding.DetailFragmentBinding
import com.redbubble.redbubblehomework.ui.main.HomeAdapter
import com.redbubble.redbubblehomework.ui.main.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : BaseFragment() {
    lateinit var viewModel: DetailFragmentViewModel
    val safeArgs: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var homeAdapter: HomeAdapter

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(DetailFragmentViewModel::class.java)
        return DetailFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            adapter = homeAdapter
            glide = requestManager
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()
        viewModel.setId(safeArgs.id)
    }

    private fun subscribeUi() {
        viewModel.workDetail.observe(viewLifecycleOwner, { items ->
            items?.let { homeAdapter.updateData(it.availableProducts) }
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->
            error?.let { showSnackBar(error) }
        })
    }
}


