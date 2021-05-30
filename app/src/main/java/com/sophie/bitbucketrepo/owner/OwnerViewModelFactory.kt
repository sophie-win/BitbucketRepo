package com.sophie.bitbucketrepo.owner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sophie.bitbucketrepo.OwnerRepository
import java.lang.IllegalArgumentException

class OwnerViewModelFactory(private val repository: OwnerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OwnerViewModel::class.java)) {
            return OwnerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }
}