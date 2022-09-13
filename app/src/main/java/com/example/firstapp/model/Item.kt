package com.example.firstapp.model
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*


data class GithubTrending(
    @SerializedName("items")
    val items: List<Item>

)
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Owner::class.java.classLoader),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeString(full_name)
        parcel.writeString(name)
        parcel.writeParcelable(owner, flags)
        parcel.writeString(default_branch)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

}

