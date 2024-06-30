package com.example.islami.domian.repo

import com.example.islami.ui.data_models.RadioResponse

interface RadioRepository {
    suspend fun getRadios() : RadioResponse
}