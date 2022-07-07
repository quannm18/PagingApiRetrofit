package com.example.clonepaging.network

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("character")
    suspend fun getDataFromAPI(@Query("page") page: Int) : RickAndMortyList
}