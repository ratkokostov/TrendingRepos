package com.example.firstapp.model

import androidx.room.ColumnInfo
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
)

class TypeConverterOwner {

    val gson: Gson = Gson()

    @TypeConverter
    fun stringToObjectList(data: String?): Owner? {
        if (data == null) return null
        val listType: Type = object : TypeToken<Owner?>() {}.type
        return gson.fromJson<Owner?>(data, listType)
    }

    @TypeConverter
    fun objectListToString(data: Owner?): String? {
        return gson.toJson(data)
    }
}