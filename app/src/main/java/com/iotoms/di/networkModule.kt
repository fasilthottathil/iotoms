package com.iotoms.di

import com.iotoms.data.remote.api.ApiClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

/**
 * Created by Fasil on 26/10/2025
 */
val networkModule = module {
    single<HttpClient> { ApiClient().getClient(OkHttp.create()) }
}