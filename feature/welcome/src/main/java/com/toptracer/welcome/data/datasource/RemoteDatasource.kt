package com.toptracer.welcome.data.datasource

import com.toptracer.welcome.domain.entity.GiphyEntity

interface RemoteDatasource {

    suspend fun getAllGiphy(apiKey: String): Result<GiphyEntity>

}
