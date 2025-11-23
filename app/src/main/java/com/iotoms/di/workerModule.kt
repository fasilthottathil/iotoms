package com.iotoms.di

import com.iotoms.data.worker.DataSyncWorker
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

/**
 * Created by Fasil on 23/11/2025
 */
val workerModule = module {
    worker { DataSyncWorker(get(), get(), get()) }
}