package com.example.firstapp.di

import android.content.Context
import androidx.room.Room
import com.example.firstapp.dataLayer.GithubTrendingReposDAO
import com.example.firstapp.dataLayer.RoomDataBase
import com.example.firstapp.repository.MainRepository
import com.example.firstapp.network.SearchGithubServiceApi
import com.example.firstapp.network.SearchReadMeServiceApi
import com.example.firstapp.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

const val BASE_URL_REPOS = "https://api.github.com"
const val BASE_URL_README = "https://raw.githubusercontent.com"

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
    fun provideApiServiceForReadme(
    ): SearchReadMeServiceApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_README)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(SearchReadMeServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceForRepos(
    ): SearchGithubServiceApi {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchGithubServiceApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface Binding {
        @Binds
        @Singleton
        fun bindMainRepository(mainRepository: MainRepositoryImpl): MainRepository
    }

}