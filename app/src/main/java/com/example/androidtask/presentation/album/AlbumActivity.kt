package com.example.androidtask.presentation.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.R
import com.example.androidtask.presentation.adapter.AlbumRvAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() {

    @Inject lateinit var albumRvAdapter:AlbumRvAdapter
    private lateinit var albumRecyclerView: RecyclerView
    private val albumViewModel: AlbumViewModel by viewModels<AlbumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        albumRecyclerView = findViewById(R.id.album_rv)
        albumRecyclerView.adapter = albumRvAdapter
        albumViewModel.albumList.observe(this, Observer {
            albumRvAdapter.setAlbums(it)
        })
        albumRvAdapter.callGetPhotos = { albumId, position ->

        }



    }
}