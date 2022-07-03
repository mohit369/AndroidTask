package com.example.androidtask.presentation.album


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtask.common.Resource
import com.example.androidtask.data.DatabaseDao
import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.model.Photos
import com.example.androidtask.domain.use_case.GetAlbumUseCase
import com.example.androidtask.domain.use_case.GetPhotosUseCase
import com.example.androidtask.presentation.adapter.PhotoRvAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val getAlbumUseCase: GetAlbumUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,private val dao: DatabaseDao):ViewModel() {

    var albumList: MutableLiveData<List<AlbumsItem>> = MutableLiveData<List<AlbumsItem>>()
    var isLoading = MutableLiveData<Boolean>()

    init {
        getAlbums()
    }

    private fun getAlbums() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = dao.getAlbums()
            withContext(Dispatchers.Main) {
                list.let { albumList.postValue(it) }
            }
        }



        getAlbumUseCase().onEach { result ->
            when(result){
                is Resource.Success ->{
                    isLoading.postValue(false)
                    if (dao.getAlbums().isEmpty()){
                        albumList.postValue(result.data)
                    }
                    dao.deleteAlbums()
                    result.data?.let { dao.insertAlbums(it) }
                }
                is Resource.Loading ->{
                    isLoading.postValue(true)
                }
                is  Resource.Error ->{
                    isLoading.postValue(false)
                }

            }
        }.launchIn(viewModelScope)
    }

    fun getPhotosById(albumId: Int, adapter: PhotoRvAdapter) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = dao.getPhotos(albumId)
            withContext(Dispatchers.Main) {
                list.let { adapter.setPhotos(it) }
            }
        }

        getPhotosUseCase(albumId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    isLoading.postValue(false)
                    if (dao.getPhotos(albumId).isEmpty()){
                        adapter.setPhotos(result.data!!)
                    }
                    dao.deletePhotos()
                   result.data?.let { dao.insertPhotos(it) }
                }
                is Resource.Loading -> {
                    isLoading.postValue(true)
                }
                is Resource.Error -> {
                    isLoading.postValue(false)
                }
            }
        }.launchIn(viewModelScope)
    }

}