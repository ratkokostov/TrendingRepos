package com.example.firstapp.di

import android.content.Context
import androidx.room.Room
import com.example.firstapp.dataLayer.GithubTrendingReposDAO
import com.example.firstapp.dataLayer.RoomDataBase
import com.example.firstapp.repository.MainRepository
import com.example.firstapp.network.GithubBaseUrlProvider
import com.example.firstapp.network.GithubBaseUrlProviderImpl
import com.example.firstapp.network.SearchGithubServiceApi
import com.example.firstapp.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule.Binding::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(roomDataBase: RoomDataBase) : GithubTrendingReposDAO =
        roomDataBase.githubReposDAO()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : RoomDataBase
    = Room.databaseBuilder(
        context,
        RoomDataBase::class.java,
        "repos_db"
    ).fallbackToDestructiveMigration()
        .build()

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
        fun bindGithubBaseUrlProvider(githubBaseUrlProvider: GithubBaseUrlProviderImpl): GithubBaseUrlProvider
    }

}