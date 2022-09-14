package com.example.firstapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Type

@kotlinx.parcelize.Parcelize
data class Owner(
    @SerializedName("login")
    val login: String?,
    @SerializedName("avatar_url")
    val avatarUrl: String?
) : Parcelable

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