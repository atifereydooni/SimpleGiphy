package com.toptracer.welcome.data.datasource

import com.toptracer.welcome.data.api.ApiService
import com.toptracer.welcome.domain.entity.GiphyEntity
import retrofit2.HttpException

class RemoteDatasourceImpl(private val api: ApiService) :
    RemoteDatasource {

    override suspend fun getAllGiphy(apiKey: String): Result<GiphyEntity> {
        return handleRequest {
            api.getAllGiphy(apiKey)
        }
    }

}

suspend fun <T : Any> handleRequest(requestFunc: suspend () -> T): Result<T> {
    return try {
        Result.success(requestFunc.invoke())
    } catch (e: HttpException) {
        when {
            e.code() == 400 -> Result.failure(Throwable(e.message()))
            else -> Result.failure(e)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
