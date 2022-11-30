package com.toptracer.welcome.domain.usecase

import com.toptracer.welcome.domain.entity.GiphyEntity
import com.toptracer.welcome.domain.repo.GiphyRepository
import javax.inject.Inject

class GetGiphyUseCase @Inject constructor(private val repository: GiphyRepository) {

    suspend fun getAllGiphy(apiKey: String): Result<GiphyEntity> {
        return repository.getAllGiphy(apiKey)
    }
}
