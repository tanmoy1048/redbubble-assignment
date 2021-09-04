package com.redbubble.redbubblehomework.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth.assertThat
import com.redbubble.redbubblehomework.data.repository.FakeRepository
import com.redbubble.redbubblehomework.launchFragmentInHiltContainer
import com.redbubble.redbubblehomework.utils.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class DetailFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var fakeRepository: FakeRepository
    lateinit var viewModel: DetailFragmentViewModel

    @Before
    fun init() {
        hiltRule.inject()
        fakeRepository = FakeRepository()
        viewModel = DetailFragmentViewModel(fakeRepository)
    }

    @Test
    fun test_detail_items_get_data() {
        val fragmentArgs = bundleOf("id" to "123456")
        launchFragmentInHiltContainer<DetailFragment>(fragmentArgs) {
            (this as DetailFragment).viewModel = viewModel
        }
        //argument is not important here
        viewModel.setId("123456")
        assertThat(viewModel.workDetail.getOrAwaitValue()?.availableProducts).contains(
            fakeRepository.item
        )
        assertThat(viewModel.workDetail.getOrAwaitValue()?.artist).isEqualTo(fakeRepository.artist)
    }
}