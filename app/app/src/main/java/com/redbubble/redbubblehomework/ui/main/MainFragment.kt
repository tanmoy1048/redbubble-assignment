package com.redbubble.redbubblehomework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.redbubble.redbubblehomework.R
import com.redbubble.redbubblehomework.data.model.Result
import com.redbubble.redbubblehomework.ui.main.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {
    private val viewModel: HomeFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()
        viewModel.fetchHomeItems()
    }

    private fun subscribeUi() {
        viewModel.homeResponse.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Failure -> {
                    showSnackBar(result.message)
                }
                is Result.Loading -> {
                }
                is Result.Success -> {
                    result.data?.home?.let { }
                }
            }
        })
    }
}


