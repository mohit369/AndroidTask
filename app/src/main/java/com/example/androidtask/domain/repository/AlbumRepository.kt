package com.example.androidtask.domain.repository

import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.model.Photos

interface AlbumRepository {

    suspend fun getAlbums():List<AlbumsItem>

    suspend fun getPhotos(albumId:Int):List<Photos>
}