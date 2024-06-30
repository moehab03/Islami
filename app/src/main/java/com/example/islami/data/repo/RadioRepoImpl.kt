package com.example.islami.data.repo

import com.example.islami.data.api.WebServices
import com.example.islami.domian.repo.RadioRepository
import com.example.islami.ui.data_models.RadioResponse
import javax.inject.Inject

class RadioRepoImpl @Inject constructor(private val webServices: WebServices) : RadioRepository {
    override suspend fun getRadios(): RadioResponse {
        return webServices.getRadios()
    }
}