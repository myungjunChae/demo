package com.ono.simple

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun injectionFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModules,
            remoteModules
        )
    )
}

val viewModules: Module = module {
    viewModel { MainViewModel(get()) }
}

val remoteModules: Module = module {
    single { testApi }
}

private const val BASE_URL = "https://my-json-server.typicode.com/myungjunChae/demo/"


private val retrofit: Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

private val testApi: TestApi = retrofit.create(TestApi::class.java)