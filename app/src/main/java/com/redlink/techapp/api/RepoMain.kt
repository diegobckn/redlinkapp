package com.redlink.techapp.api

import com.redlink.techapp.domain.Resource
import com.redlink.techapp.domain.model.Album
import com.redlink.techapp.domain.model.Photo

class RepoMain(private val dataSource: Resource) : Repo {
    override suspend fun getAlbumList(): Request<List<Album>> {
        return dataSource.getAlbumes()
    }

    override suspend fun getAlbumByTitle(title:String): Request<List<Album>> {
        return dataSource.getAlbumByTitle(title)
    }

    override suspend fun getPhotoByAlbumId(albumId: Int): Request<List<Photo>>{
        return dataSource.getPhotoByAlbumId(albumId)
    }
}