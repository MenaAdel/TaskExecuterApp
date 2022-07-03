package com.example.taskexecuterapp.core.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.taskexecuterapp.domain.ApiImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class GetterWorker(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams) {

    private val apiImplementation = ApiImplementation()

    override suspend fun doWork(): Result {
        return try {
            coroutineScope  {
                try {
                    Log.d("worker", "inside with context")
                    apiImplementation.getDogImage()
                    Result.success()

                } catch (e: Exception){
                    Log.d("worker", "exception ${e.message}")
                    Result.failure()
                }
            }
        }catch (e:Exception){
            Log.d("worker", "exception ${e.message}")
            Result.failure()
        }
    }
}