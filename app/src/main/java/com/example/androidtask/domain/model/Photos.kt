package com.example.androidtask.domain.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Photos(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)