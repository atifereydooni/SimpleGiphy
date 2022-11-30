package com.toptracer.welcome.data

import com.toptracer.welcome.data.datasource.RemoteDatasource
import com.toptracer.welcome.domain.entity.GiphyEntity
import com.toptracer.welcome.domain.repo.GiphyRepository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remote: RemoteDatasource) :
    GiphyRepository {

    override suspend fun getAllGiphy(apiKey: String): Result<GiphyEntity> {
        return remote.getAllGiphy(apiKey)
    }

}
