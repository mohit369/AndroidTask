package com.example.androidtask.domain.use_case

import com.example.androidtask.common.Resource
import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.model.Photos
import com.example.androidtask.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val albumRepository: AlbumRepository){

    operator  fun invoke(albumId:Int): Flow<Resource<List<Photos>>> = flow {
        try {
            emit(Resource.Loading<List<Photos>>())
            val photos = albumRepository.getPhotos(albumId)
            emit(Resource.Success<List<Photos>>(photos))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Photos>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Photos>>("Couldn't reach server. Check your internet connection."))
        }
    }

}