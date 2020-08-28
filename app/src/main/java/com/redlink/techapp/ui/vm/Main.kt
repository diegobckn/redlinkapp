package com.redlink.techapp.ui.vm

import androidx.lifecycle.*
import com.redlink.techapp.api.Repo
import com.redlink.techapp.api.Request
import kotlinx.coroutines.Dispatchers

class Main (private val repo: Repo): ViewModel() {

    private val albumTitle = MutableLiveData<String>()
    private val albumId = MutableLiveData<Int>()

    fun setTitle(title: String){
        albumTitle.value = title
    }

    fun setAlbumId(id: Int){
        albumId.value = id
    }

    init{
        setTitle("")
    }

    val fetchAlbumList = albumTitle.distinctUntilChanged().switchMap { title->
        liveData(Dispatchers.IO){
            emit(Request.Loading())
            try {
                if(title.isEmpty()) {
                    emit(repo.getAlbumList())
                }else{
                    emit(repo.getAlbumByTitle(title))
                }
            }
            catch (e:Exception){
                emit(Request.Failure(e))
            }
        }
    }

    val fetchPhotoList = albumId.distinctUntilChanged().switchMap { albumId->
        liveData(Dispatchers.IO){
            emit(Request.Loading())
            try {
                emit(repo.getPhotoByAlbumId(albumId))
            }
            catch (e:Exception){
                emit(Request.Failure(e))
            }
        }
    }

}