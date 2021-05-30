package com.sophie.bitbucketrepo.owner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sophie.bitbucketrepo.OwnerRepository
import com.sophie.bitbucketrepo.RefreshError
import kotlinx.coroutines.launch

class OwnerViewModel(private val repository: OwnerRepository): ViewModel() {
    val value = repository.value
    val nextBtnClickable = false

    private val _spinner = MutableLiveData<Boolean>(false)
    val spinner: LiveData<Boolean>
        get() = _spinner


    fun refreshOwner() {
        viewModelScope.launch {
            try {
                _spinner.value = true
                repository.refreshOwners()
            } catch (error: RefreshError) {

            } finally {
                _spinner.value = false
            }
        }
    }

    fun nextOwnerList(url: String) {
        viewModelScope.launch {
            try {
                _spinner.value = true
                repository.getNextOwners(url)
            } catch (error: RefreshError) {

            } finally {
                _spinner.value = false
            }
        }
    }
}