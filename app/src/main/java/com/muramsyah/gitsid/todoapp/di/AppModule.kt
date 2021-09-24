package com.muramsyah.gitsid.todoapp.di

import com.muramsyah.gitsid.todoapp.data.source.remote.RemoteDataSource
import com.muramsyah.gitsid.todoapp.data.source.remote.network.ApiSevice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideRepository(apiService: ApiSevice): RemoteDataSource = RemoteDataSource(apiService)

}