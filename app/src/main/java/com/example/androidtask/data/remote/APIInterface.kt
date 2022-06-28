package com.example.androidtask.data.remote

import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.model.Photos
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("albums")
    suspend fun getAlbums():List<AlbumsItem>

    @GET("photos")
    suspend fun getPhotos(@Query("albumId") albumId:Int):List<Photos>

}