package com.redbubble.redbubblehomework.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.redbubble.redbubblehomework.R
import com.redbubble.redbubblehomework.data.remote.NetworkServiceApi
import com.redbubble.redbubblehomework.data.repository.MainDataSource
import com.redbubble.redbubblehomework.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(networkServiceApi: NetworkServiceApi): MainDataSource{
        return MainRepository(networkServiceApi)
    }
}