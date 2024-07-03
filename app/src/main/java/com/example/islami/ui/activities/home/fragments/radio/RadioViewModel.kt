package com.example.islami.ui.activities.home.fragments.radio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islami.domian.use_cases.RadioUseCase
import com.example.islami.ui.data_models.RadioStation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadioViewModel @Inject constructor(private val useCase: RadioUseCase) : ViewModel() {
    val radioStations = MutableLiveData<List<RadioStation?>?>()

    fun getRadiosList() {
        viewModelScope.launch {
            val response =useCase.execute()
            radioStations.value = response.radios
        }
    }

}