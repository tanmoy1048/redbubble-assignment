package com.redbubble.redbubblehomework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestNetworkModule {
    const val MOCK_WEB_SERVER_PORT = 8000

    @Provides
    @Named("test_retrofit")
    fun provideMockRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
    }

    @Provides
    @Named("mockserver")
    fun provideMockServer() = MockWebServer()
}