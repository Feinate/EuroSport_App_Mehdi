package com.mhdncb.eurosportappmehdi.data.repository

import android.content.ContentValues
import android.util.Log
import com.mhdncb.eurosportappmehdi.data.service.RetrofitService
import com.mhdncb.eurosportappmehdi.domain.entity.Articles
import com.mhdncb.eurosportappmehdi.domain.repository.ArticleRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class ArticleRepositoryImpl(
    private val retrofitService: RetrofitService
) : ArticleRepository, CoroutineScope {

    override val coroutineContext: CoroutineContext =
        Dispatchers.IO +
                SupervisorJob() +
                CoroutineExceptionHandler { _, exception -> Log.e(ContentValues.TAG, exception.localizedMessage?:"Error Coroutine") }

    override suspend fun getArticles(): Response<Articles> {
        return retrofitService.getPost()
    }

}