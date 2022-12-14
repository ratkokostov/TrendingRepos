package com.example.firstapp.model
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*


data class GithubTrending(
    @SerializedName("items")
    val items: List<Item>

)
@Parcelize
@Entity(tableName = "github_trending_repos")
data class Item(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "full_name")
    val full_name: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "owner")
    val owner: Owner?,
    @ColumnInfo(name = "default_branch")
    val default_branch: String?
) : Parcelable

