package com.example.taskexecuterapp.domain

import com.example.taskexecuterapp.core.entity.ResponseEntity
import retrofit2.Response
import retrofit2.http.GET

interface GetDogData {
    @GET("random")
    suspend fun getDogData():Response<ResponseEntity>
}