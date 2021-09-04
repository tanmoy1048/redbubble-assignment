package com.redbubble.redbubblehomework.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
class MainFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var fakeRepository: FakeRepository
    lateinit var viewModel: HomeFragmentViewModel

    @Before
    fun init() {
        hiltRule.inject()
        fakeRepository = FakeRepository()
        viewModel = HomeFragmentViewModel(fakeRepository)
    }

    @Test
    fun test_homeItems_get_data() {
        launchFragmentInHiltContainer<MainFragment> {
            (this as MainFragment).viewModel = viewModel
        }
        viewModel.fetchHomeItems()
        assertThat(viewModel.homeItems.getOrAwaitValue()).contains(fakeRepository.item)
    }
}