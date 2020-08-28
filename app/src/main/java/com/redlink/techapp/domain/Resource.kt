package com.redlink.techapp.domain

import com.redlink.techapp.api.Request
import com.redlink.techapp.api.RetrofitConnector
import com.redlink.techapp.domain.model.Album
import com.redlink.techapp.domain.model.Photo

class Resource {
    suspend fun getAlbumes(): Request<List<Album>>{
        return Request.Success(RetrofitConnector.webService.getAlbumList())
    }

    suspend fun getAlbumByTitle(title: String): Request<List<Album>>{
        return Request.Success(RetrofitConnector.webService.getAlbumByTitle(title))
    }

    suspend fun getPhotoByAlbumId(albumId: Int): Request<List<Photo>>{
        return Request.Success(RetrofitConnector.webService.getPhotoByAlbumId(albumId))
    }
}