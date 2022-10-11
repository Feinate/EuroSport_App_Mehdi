package com.mhdncb.eurosportappmehdi.di

import android.content.Context
import com.mhdncb.eurosportappmehdi.data.RetrofitInstance
import com.mhdncb.eurosportappmehdi.data.service.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MyApplication {
        return app as MyApplication
    }

    @Singleton
    @Provides
    fun providesRetrofitService(): RetrofitService = RetrofitInstance.api

}