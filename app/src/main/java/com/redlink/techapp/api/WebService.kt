package com.redlink.techapp.api

import com.redlink.techapp.domain.model.Album
import com.redlink.techapp.domain.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("albums")
    suspend fun getAlbumList() : List<Album>

    @GET("albums")
    suspend fun getAlbumByTitle(@Query("title") title: String) : List<Album>

    @GET("photos")
    suspend fun getPhotoByAlbumId(@Query("albumId") albumId: Int) : List<Photo>
}