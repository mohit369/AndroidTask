package com.example.androidtask.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.R
import com.example.androidtask.domain.model.AlbumsItem
import javax.inject.Inject

class AlbumRvAdapter@Inject constructor():RecyclerView.Adapter<AlbumRvAdapter.ViewHolder>() {

    var albumList = mutableListOf<AlbumsItem>()
    private lateinit var adapter:PhotoRvAdapter

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.findViewById(R.id.album_title)
        val photosRv:RecyclerView = itemView.findViewById(R.id.horizontal_photo_rv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.album_item_rv,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = albumList[position].title
        adapter = PhotoRvAdapter()
        holder.photosRv.adapter = adapter


    }

    override fun getItemCount(): Int {
       return  albumList.size
    }



    fun setAlbums(albums: List<AlbumsItem>) {
        this.albumList = albums.toMutableList()
        notifyDataSetChanged()
    }
}