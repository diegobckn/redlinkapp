package com.redlink.techapp.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.redlink.techapp.R
import com.redlink.techapp.domain.model.Photo
import com.redlink.techapp.ui.AppViewHolder
import kotlinx.android.synthetic.main.photo_rows.view.*

class PhotoAdapter (private val context: Context, private val photoList: List<Photo>) :
    RecyclerView.Adapter<AppViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.photo_rows, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: AppViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(photoList[position], position)
        }

    }

    inner class MainViewHolder(itemView: View) : AppViewHolder<Photo>(itemView) {
        override fun bind(item: Photo, position: Int) {
            Glide.with(context)
//                .load(item.thumbnailUrl)
                .load("https://pbs.twimg.com/profile_images/1214179542849314816/MokvCV0O_400x400.png")
                .centerCrop().into(itemView.image)


            Log.d("asd","------------imagen:::: ${item.thumbnailUrl} ---------------")
            itemView.photoId.text = item.id.toString()
            itemView.photoTitle.text = item.title
        }

    }
}