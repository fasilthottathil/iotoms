package com.iotoms

import android.app.Application
import com.iotoms.di.networkModule
import com.iotoms.di.repositoryModule
import com.iotoms.di.useCaseModule
import com.iotoms.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Fasil on 26/10/2025
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}