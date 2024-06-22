package org.m0skit0.android.mondlycodetask.data

import retrofit2.http.GET

interface ItemsService {
    @GET("/mondly_android_code_task_json")
    suspend fun items(): List<ItemsDTO>
}
