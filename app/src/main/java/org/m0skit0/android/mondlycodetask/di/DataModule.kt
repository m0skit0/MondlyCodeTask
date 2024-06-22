package org.m0skit0.android.mondlycodetask.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import org.m0skit0.android.mondlycodetask.data.ItemsDTO
import org.m0skit0.android.mondlycodetask.data.ItemsService
import retrofit2.Retrofit
import retrofit2.create

@OptIn(ExperimentalStdlibApi::class)
val dataModule = module {

    single {
         Moshi.Builder()
             .add(KotlinJsonAdapterFactory())
             .build()
    }

    single<JsonAdapter<ItemsDTO>> {
        get<Moshi>().adapter()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://europe-west1-mondly-workflows.cloudfunctions.net")
            .build()
    }

    single<ItemsService> {
        get<Retrofit>().create()
    }
}
