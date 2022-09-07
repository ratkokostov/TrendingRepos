package com.example.firstapp.dataLayer

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Item
import com.example.firstapp.model.TypeConverterOwner

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverterOwner::class)
abstract class RoomDataBase : RoomDatabase(){

    abstract fun githubReposDAO() : GithubTrendingReposDAO
}