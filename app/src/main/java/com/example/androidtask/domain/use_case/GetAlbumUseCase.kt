package com.example.androidtask.domain.use_case

import com.example.androidtask.common.Resource
import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAlbumUseCase @Inject constructor(private val albumRepository: AlbumRepository){

    operator  fun invoke():Flow<Resource<List<AlbumsItem>>> = flow {
        try {
            emit(Resource.Loading<List<AlbumsItem>>())
            val albums = albumRepository.getAlbums()
            emit(Resource.Success<List<AlbumsItem>>(albums))
        } catch(e: HttpException) {
        emit(Resource.Error<List<AlbumsItem>>(e.localizedMessage ?: "An unexpected error occured"))
    } catch(e: IOException) {
        emit(Resource.Error<List<AlbumsItem>>("Couldn't reach server. Check your internet connection."))
    }
    }
}