package com.iotoms

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.work.Configuration
import com.iotoms.di.networkModule
import com.iotoms.di.repositoryModule
import com.iotoms.di.storageModule
import com.iotoms.di.useCaseModule
import com.iotoms.di.viewModelModule
import com.iotoms.di.workerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

/**
 * Created by Fasil on 26/10/2025
 */
class App : Application() , Configuration.Provider{
    override fun onCreate() {
        super.onCreate()

        createSyncNotificationChannel()
        startKoin {
            androidContext(this@App)
            workManagerFactory()
            androidLogger()
            modules(
                storageModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                workerModule
            )
        }
    }

    private fun createSyncNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "sync", // MUST match Builder channelId
                "Data Sync",
                NotificationManager.IMPORTANCE_LOW // foreground-safe
            ).apply {
                description = "Shows data synchronization progress"
                setShowBadge(false)
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(KoinWorkerFactory())
            .build()
}