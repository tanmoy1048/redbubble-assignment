package com.redbubble.redbubblehomework.data.repository

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.redbubble.redbubblehomework.data.model.Result
import com.redbubble.redbubblehomework.data.remote.NetworkServiceApi
import com.redbubble.redbubblehomework.di.TestNetworkModule.MOCK_WEB_SERVER_PORT
import com.redbubble.redbubblehomework.utils.MockResponseFileReader
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class MainRepositoryTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("mockserver")
    lateinit var mockWebServer: MockWebServer

    @Inject
    @Named("test_retrofit")
    lateinit var retrofitBuilder: Retrofit.Builder

    lateinit var networkServiceApi: NetworkServiceApi
    private lateinit var mainRepository: MainRepository

    @Before
    fun init() {
        hiltRule.inject()
        mockWebServer.start(MOCK_WEB_SERVER_PORT)
        networkServiceApi = retrofitBuilder
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(NetworkServiceApi::class.java)

        mainRepository = MainRepository(networkServiceApi)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun badRequestTest() {
        runBlocking {
            mockWebServer.apply {
                enqueue(
                    MockResponse().setBody(
                        MockResponseFileReader("error_response.json").content
                    ).setResponseCode(400)
                )
            }

            val jobResponse = mainRepository.getHomeItems().toList()
            assertThat(jobResponse.first()).isInstanceOf(Result.Loading::class.java)
            assertThat(jobResponse.drop(1).first()).isInstanceOf(Result.Failure::class.java)
        }
    }

    @Test
    fun happyRequestTest() {
        runBlocking {
            mockWebServer.apply {
                enqueue(
                    MockResponse().setBody(
                        MockResponseFileReader("happy_response.json").content
                    )
                )
            }

            val jobResponse = mainRepository.getHomeItems().toList()
            assertThat(jobResponse.first()).isInstanceOf(Result.Loading::class.java)
            val response = jobResponse.drop(1).first()
            assertThat(jobResponse.drop(1).first()).isInstanceOf(Result.Success::class.java)
            val data = (response as Result.Success).data?.home
            assertThat(data?.size).isEqualTo(2)
        }
    }


    @Test
    fun happyDetailRequestTest() {
        runBlocking {
            mockWebServer.apply {
                enqueue(
                    MockResponse().setBody(
                        MockResponseFileReader("happy_detail_response.json").content
                    )
                )
            }

            val jobResponse = mainRepository.getItemDetail("anything").toList()
            assertThat(jobResponse.first()).isInstanceOf(Result.Loading::class.java)
            val response = jobResponse.drop(1).first()
            assertThat(jobResponse.drop(1).first()).isInstanceOf(Result.Success::class.java)
            val data = (response as Result.Success).data?.workDetails
            assertThat(data?.artist?.username).isEqualTo("natalietyler")
        }
    }
}