package com.mhdncb.eurosportappmehdi.di

import com.mhdncb.eurosportappmehdi.data.repository.ArticleRepositoryImpl
import com.mhdncb.eurosportappmehdi.data.service.RetrofitService
import com.mhdncb.eurosportappmehdi.domain.repository.ArticleRepository
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
    fun providePostRepository(retrofitService: RetrofitService): ArticleRepository {
        return ArticleRepositoryImpl(retrofitService)
    }

}