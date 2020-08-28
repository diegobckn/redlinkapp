package com.redlink.techapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redlink.techapp.R
import com.redlink.techapp.domain.model.Album
import com.redlink.techapp.ui.AppViewHolder
import kotlinx.android.synthetic.main.album_rows.view.*

class AlbumAdapter(private val context: Context, private val albumList: List<Album>, private val itemClickListener: OnAlbumClickListener) :
    RecyclerView.Adapter<AppViewHolder<*>>() {

    interface OnAlbumClickListener{
        fun onAlbumClick(album: Album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.album_rows, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AppViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(albumList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : AppViewHolder<Album>(itemView) {
        override fun bind(item: Album, position: Int) {
            itemView.albumId.text = item.id.toString()
            itemView.title.text = item.title
            itemView.setOnClickListener{itemClickListener.onAlbumClick(item)}
        }
    }
}