package org.m0skit0.android.mondlycodetask.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import org.m0skit0.android.mondlycodetask.data.ItemsDTO
import org.m0skit0.android.mondlycodetask.data.ItemsService
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private val BASE_URL = "https://europe-west1-mondly-workflows.cloudfunctions.net"

@OptIn(ExperimentalStdlibApi::class)
val dataModule = module {

    single<JsonAdapter.Factory> {
        KotlinJsonAdapterFactory()
    }

    single {
         Moshi.Builder()
             .add(get())
             .build()
    }

    single<JsonAdapter<ItemsDTO>> {
        get<Moshi>().adapter()
    }

    single<Converter.Factory> {
        MoshiConverterFactory.create()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get())
            .build()
    }

    single<ItemsService> {
        get<Retrofit>().create()
    }
}
