package com.example.firstapp.di

import com.example.firstapp.domain.MainRepository
import com.example.firstapp.network.ApiService
import com.example.firstapp.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule.Binding::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binding {
        @Binds
        @Singleton
        fun bindMainRepository(mainRepository: MainRepositoryImpl): MainRepository
    }

}