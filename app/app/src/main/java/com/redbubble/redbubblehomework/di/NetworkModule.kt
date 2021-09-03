package com.redbubble.redbubblehomework.di


import com.redbubble.redbubblehomework.data.remote.NetworkServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://take-home-test.herokuapp.com/bff/"

    @Provides
    @Singleton
    fun provideRetrofit() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun networkDataSource(retrofit: Retrofit): NetworkServiceApi {
        return retrofit.create(NetworkServiceApi::class.java)
    }
}