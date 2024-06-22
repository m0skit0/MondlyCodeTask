package org.m0skit0.android.mondlycodetask.data.retrofit

import org.m0skit0.android.mondlycodetask.data.ItemsDTO
import retrofit2.http.GET

interface ItemsService {
    @GET("/mondly_android_code_task_json")
    fun getItems(): List<ItemsDTO>
}
