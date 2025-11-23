package com.iotoms

import android.app.Application
import com.iotoms.di.networkModule
import com.iotoms.di.repositoryModule
import com.iotoms.di.useCaseModule
import com.iotoms.di.viewModelModule
import com.iotoms.di.workerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

/**
 * Created by Fasil on 26/10/2025
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            workManagerFactory()
            androidLogger()
            androidContext(this@App)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                workerModule
            )
        }
    }
}