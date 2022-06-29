package com.example.androidtask.di

import com.example.androidtask.common.Constant
import com.example.androidtask.data.remote.APIInterface
import com.example.androidtask.data.repository.AlbumRepositoryImpl
import com.example.androidtask.domain.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiInstance() : APIInterface{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(api:APIInterface) : AlbumRepository{
        return AlbumRepositoryImpl(api)
    }

}