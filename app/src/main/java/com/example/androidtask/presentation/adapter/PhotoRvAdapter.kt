package com.example.androidtask.presentation.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
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

        val photo:ImageView = itemView.findViewById(R.id.photos_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos_item_rv,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (photoList.isNotEmpty()) {
            val theImage = GlideUrl(
                photoList[position % photoList.size].thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "5")
                    .build()
            )

            theImage.let {
                Glide.with(holder.itemView.context)
                    .load(theImage)
                    .apply(
                        RequestOptions()
                            .error(R.drawable.ic_launcher_background)

                    )
                    .into(object : CustomViewTarget<ImageView, Drawable>(holder.photo) {
                        override fun onLoadFailed(errorDrawable: Drawable?) {}
                        override fun onResourceCleared(placeholder: Drawable?) {}
                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            holder.photo.setImageDrawable(resource)
                        }
                    })
            }
        }
       // Glide.with(holder.itemView.context).load(photoList[position].thumbnailUrl).override(150, 150).centerCrop().into(holder.photo)
    }

    override fun getItemCount(): Int {
      return Integer.MAX_VALUE
    }
}