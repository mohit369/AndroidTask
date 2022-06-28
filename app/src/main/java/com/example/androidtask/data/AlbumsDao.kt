package com.example.androidtask.data

import androidx.room.Dao
import androidx.room.Query
import com.example.androidtask.domain.model.AlbumsItem

@Dao
interface AlbumsDao {

    @Query("Select * from AlbumsItem")
    fun getAlbums() : List<AlbumsItem>

}