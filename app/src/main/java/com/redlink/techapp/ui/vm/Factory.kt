package com.redlink.techapp.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redlink.techapp.api.Repo

class Factory(private  val repo: Repo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
}
