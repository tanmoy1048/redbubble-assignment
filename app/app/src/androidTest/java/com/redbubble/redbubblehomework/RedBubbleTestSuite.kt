package com.redbubble.redbubblehomework

import com.redbubble.redbubblehomework.data.repository.MainRepositoryTest
import com.redbubble.redbubblehomework.ui.MainActivityTest
import com.redbubble.redbubblehomework.ui.detail.DetailFragmentTest
import com.redbubble.redbubblehomework.ui.main.MainFragmentTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite


@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    MainFragmentTest::class,
    DetailFragmentTest::class,
    MainRepositoryTest::class
)
class RedBubbleTestSuite