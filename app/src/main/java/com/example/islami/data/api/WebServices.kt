package com.example.islami.data.api

import com.example.islami.ui.data_models.RadioResponse
import retrofit2.http.GET

interface WebServices {
    @GET("radios")
    suspend fun getRadios(): RadioResponse
}