package com.example.androidtask.presentation.album


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtask.common.Resource
import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.use_case.GetAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val getAlbumUseCase: GetAlbumUseCase):ViewModel() {

    var albumList: MutableLiveData<List<AlbumsItem>> = MutableLiveData<List<AlbumsItem>>()
    var isLoading = MutableLiveData<Boolean>()

    init {
        getAlbums()
    }

    private fun getAlbums() {
        getAlbumUseCase().onEach { result ->
            when(result){
                is Resource.Success ->{
                    isLoading.postValue(false)
                    albumList.postValue(result.data ?: emptyList())
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

}