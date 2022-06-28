package com.example.androidtask.domain.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)