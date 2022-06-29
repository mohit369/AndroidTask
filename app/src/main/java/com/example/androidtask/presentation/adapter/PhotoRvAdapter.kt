package com.example.androidtask.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtask.R
import com.example.androidtask.domain.model.AlbumsItem
import com.example.androidtask.domain.model.Photos
import javax.inject.Inject

class PhotoRvAdapter@Inject constructor():RecyclerView.Adapter<PhotoRvAdapter.ViewHolder>() {

    private var photoList = mutableListOf<Photos>()

    fun setPhotos(photos: List<Photos>) {
        this.photoList = photos.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos_item_rv,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}