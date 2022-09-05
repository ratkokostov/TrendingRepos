package com.example.firstapp.model
import com.google.gson.annotations.SerializedName

data class GithubTrending(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val items: List<Item>

)

data class Item(
    @SerializedName("description")
    val description: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,

)

