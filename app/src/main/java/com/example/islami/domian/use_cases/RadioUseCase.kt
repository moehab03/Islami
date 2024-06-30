package com.example.islami.domian.use_cases

import com.example.islami.domian.repo.RadioRepository
import com.example.islami.ui.data_models.RadioResponse
import javax.inject.Inject

class RadioUseCase @Inject constructor(private val radioRepository: RadioRepository) {
    suspend fun execute(): RadioResponse {
        return radioRepository.getRadios()
    }
}