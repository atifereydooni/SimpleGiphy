package com.toptracer.welcome.data.api

import com.toptracer.welcome.domain.entity.GiphyEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("random")
    suspend fun getAllGiphy(
        @Query("api_key") apiKey: String
    ): GiphyEntity

}
