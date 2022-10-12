package com.mhdncb.eurosportappmehdi.data.service

import com.mhdncb.eurosportappmehdi.domain.entity.Articles
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("api/json-storage/bin/edfefba")
    suspend fun getPost(): Response<Articles>

}