package com.example.taskexecuterapp.presentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.example.taskexecuterapp.core.worker.GetterWorker
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel: ViewModel() {

    private val list: MutableList<String> = mutableListOf()

    val liveData by lazy { MutableLiveData<MutableList<String>>() }

    fun startWorker(context: Context ,taskName: String) {

        list.add("${getCurrentDate()} $taskName")
        liveData.postValue(list)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
        val oneTimeRequest = OneTimeWorkRequest.Builder(GetterWorker::class.java)
            .setConstraints(constraints.build())
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork("test", ExistingWorkPolicy.KEEP, oneTimeRequest)
    }
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val now = Calendar.getInstance().time
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm")
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return simpleDateFormat.format(now)
}