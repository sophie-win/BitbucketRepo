package com.sophie.bitbucketrepo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sophie.bitbucketrepo.json_shema.Root

class OwnerRepository(private val network: MainNetwork) {
    private var _value = MutableLiveData<Root>()

    val value: LiveData<Root>
        get() = _value

    suspend fun refreshOwners() {
        try {
            val response = network.fetchRepos()
            Log.e("Hey", response.toString())
            if (response != null) {
                _value.postValue(response)
//                val item = response.body()
//                if (item != null) {
//                    _value.postValue(item)
//                }
            } else {
                RefreshError("Unable to data", Throwable(response.toString()))
            }
        } catch (cause: Throwable) {
            RefreshError("Unable to data", cause)
        }
    }

    suspend fun getNextOwners(after: String) {
        try {
            val response = network.fetchNextRepos(after)
            if (response != null) {

                _value.postValue(response)
            } else {
                RefreshError("Unable to data", Throwable(response.toString()))
            }
        } catch (cause: Throwable) {
            RefreshError("Unable to data", cause)
        }

    }
}

class RefreshError(message: String, cause: Throwable?) : Throwable(message, cause)