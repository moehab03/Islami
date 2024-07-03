package com.example.islami.ui.data_models

import com.google.gson.annotations.SerializedName

data class RadioResponse(
    @field:SerializedName("radios")
    val radios: List<RadioStation?>? = null
)