package com.mhdncb.eurosportappmehdi.domain.repository

import com.mhdncb.eurosportappmehdi.domain.entity.Articles
import retrofit2.Response

interface ArticlesRepository {

    suspend fun getArticles(): Response<Articles>

}