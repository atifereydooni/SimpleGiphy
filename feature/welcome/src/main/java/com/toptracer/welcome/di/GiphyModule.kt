package com.toptracer.welcome.di

import com.toptracer.welcome.data.RepositoryImpl
import com.toptracer.welcome.data.api.ApiService
import com.toptracer.welcome.data.datasource.RemoteDatasource
import com.toptracer.welcome.data.datasource.RemoteDatasourceImpl
import com.toptracer.welcome.domain.repo.GiphyRepository
import com.toptracer.welcome.domain.usecase.GetGiphyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GiphyModule {

    @Singleton
    @Provides
    fun provideRepository(
        remote: RemoteDatasource
    ): GiphyRepository {
        return RepositoryImpl(remote = remote)
    }

    @Singleton
    @Provides
    fun providesRemoteData(@NormalRetrofitClient retrofit: Retrofit): RemoteDatasource {
        return RemoteDatasourceImpl(api = retrofit.create(ApiService::class.java))
    }

    @Singleton
    @Provides
    fun providesGiphyUseCase(repository: GiphyRepository): GetGiphyUseCase {
        return GetGiphyUseCase(repository)
    }

}
