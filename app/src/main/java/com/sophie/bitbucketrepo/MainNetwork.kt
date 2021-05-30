package com.sophie.bitbucketrepo

import com.sophie.bitbucketrepo.json_shema.Root
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private val service: MainNetwork by lazy {
    val client = OkHttpClient.Builder().build()
    val retrofit = Retrofit.Builder()
            .baseUrl("https://api.bitbucket.org")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    retrofit.create(MainNetwork::class.java)
}

fun getNetworkService() = service

interface MainNetwork {
    @GET("/2.0/repositories")
    suspend fun fetchRepos(): Root

    @GET
    suspend fun fetchNextRepos(@Url after: String): Root
}


