package com.extrime.myrandomusers.data.api

import com.extrime.myrandomusers.data.api.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET("api/")
    suspend fun getRandomUsers(
        @Query("results") results: Int = 20,
        @Query("gender") gender: String? = null,
        @Query("nat") nationality: String? = null
    ): ApiResponse
}