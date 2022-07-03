package com.example.androidtask.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.model.Photos

@Dao
interface DatabaseDao {

    @Query("Select * from AlbumsItem")
    suspend fun getAlbums() : List<AlbumsItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(list: List<AlbumsItem>)

    @Query("Select * from Photos Where albumId=:id")
    suspend fun getPhotos(id:Int) : List<Photos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(list: List<Photos>)

}