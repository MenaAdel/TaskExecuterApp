package com.example.taskexecuterapp.domain

import com.example.taskexecuterapp.core.network.buildApi

class ApiImplementation(private val gateway: GetDogData = buildApi()) {
    suspend fun getDogImage() {
        gateway.getDogData()
    }
}