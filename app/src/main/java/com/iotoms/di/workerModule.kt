package com.iotoms.di

import androidx.work.WorkManager
import com.iotoms.data.worker.DataSyncWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

/**
 * Created by Fasil on 23/11/2025
 */
val workerModule = module {
    worker { DataSyncWorker(get(), get(), get()) }
    single { WorkManager.getInstance(androidContext()) }
}