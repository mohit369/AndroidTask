package com.example.androidtask.data.repository

import com.example.androidtask.data.remote.APIInterface
import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.model.Photos
import com.example.androidtask.domain.repository.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(private val api:APIInterface): AlbumRepository {

    override suspend fun getAlbums(): List<AlbumsItem> {
        return api.getAlbums()
    }

    override suspend fun getPhotos(albumId:Int): List<Photos> {
       return api.getPhotos(albumId)
    }
}