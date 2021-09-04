package com.redbubble.redbubblehomework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.redbubble.redbubblehomework.databinding.MainFragmentBinding
import com.redbubble.redbubblehomework.ui.main.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment() {
    lateinit var viewModel: HomeFragmentViewModel

    @Inject
    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        return MainFragmentBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            adapter = homeAdapter
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter.setOnItemClickListener {
            view.findNavController()
                .navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
        }
        subscribeUi()
        viewModel.fetchHomeItems()
    }

    private fun subscribeUi() {
        viewModel.homeItems.observe(viewLifecycleOwner, { items ->
            items?.let { homeAdapter.updateData(it) }
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->
            error?.let { showSnackBar(error) }
        })
    }
}


