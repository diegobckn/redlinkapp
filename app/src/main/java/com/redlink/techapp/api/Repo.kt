package com.redlink.techapp.api

import com.redlink.techapp.domain.model.Album
import com.redlink.techapp.domain.model.Photo

interface Repo {
    suspend fun getAlbumList(): Request<List<Album>>

    suspend fun getAlbumByTitle(title: String): Request<List<Album>>

    suspend fun getPhotoByAlbumId(albumId: Int): Request<List<Photo>>

}