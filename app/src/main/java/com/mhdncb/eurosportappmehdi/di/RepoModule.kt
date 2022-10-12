package com.mhdncb.eurosportappmehdi.di

import com.mhdncb.eurosportappmehdi.data.repository.ArticlesRepositoryImpl
import com.mhdncb.eurosportappmehdi.data.service.RetrofitService
import com.mhdncb.eurosportappmehdi.domain.repository.ArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideArticlesRepository(retrofitService: RetrofitService): ArticlesRepository {
        return ArticlesRepositoryImpl(retrofitService)
    }

}