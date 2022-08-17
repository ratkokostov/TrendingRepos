package com.example.firstapp.di

import com.example.firstapp.BuildConfig
import com.example.firstapp.domain.MainRepository
import com.example.firstapp.network.GithubBaseUrlProvider
import com.example.firstapp.network.GithubBaseUrlProviderImpl
import com.example.firstapp.network.SearchGithubServiceApi
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
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofitBuilder: Retrofit.Builder,
        githubBaseUrlProvider: GithubBaseUrlProvider
    ): SearchGithubServiceApi {
        return retrofitBuilder
            .baseUrl(githubBaseUrlProvider.getUrl())
            .build()
            .create(SearchGithubServiceApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binding {
        @Binds
        @Singleton
        fun bindMainRepository(mainRepository: MainRepositoryImpl): MainRepository

        @Binds
        @Singleton
        fun bindGithubBaseUrlProvider(githubBaseUrlProvider : GithubBaseUrlProviderImpl) : GithubBaseUrlProvider
    }

}