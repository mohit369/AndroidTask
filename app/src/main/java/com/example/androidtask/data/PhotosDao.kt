package com.example.androidtask.data

import androidx.room.Dao
import androidx.room.Query
import com.example.androidtask.domain.model.Photos

@Dao
interface PhotosDao {

    @Query("Select * from Photos")
    fun getPhotos() : List<Photos>
}