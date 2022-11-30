package com.toptracer.welcome.domain.repo

import com.toptracer.welcome.domain.entity.GiphyEntity

interface GiphyRepository {

    suspend fun getAllGiphy(apiKey: String): Result<GiphyEntity>

}
