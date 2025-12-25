package com.iotoms.di

import androidx.room.Room
import com.iotoms.data.local.db.AppDatabase
import com.iotoms.data.local.pref.AppPreference
import org.koin.dsl.module

/**
 * Created by Fasil on 29/11/2025
 */
val storageModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = AppDatabase::class.java,
            name = "iotoms.db"
        ).build()
    }
    single<AppPreference> { AppPreference(context = get()) }
}