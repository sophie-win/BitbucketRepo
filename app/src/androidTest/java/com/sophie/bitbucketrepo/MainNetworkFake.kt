package com.sophie.bitbucketrepo

import com.sophie.bitbucketrepo.json_schema.Root
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class MainNetworkFake(var data: Root): MainNetwork {

    private val insertedForNext = Channel<Root>(capacity = Channel.BUFFERED)

    override suspend fun fetchRepos(): Root {
        insertedForNext.offer(data)
        return data
    }

    override suspend fun fetchNextRepos(after: String) = data

    fun loadingDataOrNull(timeout: Long = 2_000): String? {
        var result: String? = null
        runBlocking {
            // wait for the next insertion to complete
            try {
                withTimeout(timeout) {
                    result = insertedForNext.receive().next
                }
            } catch (ex: TimeoutCancellationException) {
                // ignore
            }
        }
        return result
    }

    suspend fun throwErrorWhenLoadingData(timeout: Long = 5_000): String? {
        var result: String? = null
        try {
            withTimeout(timeout) {
                result = insertedForNext.receive().next
            }
        } catch (cause: Throwable) {
            throw RefreshError("Error", cause)
        }
        return result
    }
}