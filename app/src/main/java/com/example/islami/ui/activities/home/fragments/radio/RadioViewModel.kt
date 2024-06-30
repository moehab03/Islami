package com.example.islami.ui.activities.home.fragments.radio

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.islami.domian.use_cases.RadioUseCase
import com.example.islami.ui.data_models.RadioResponse
import com.example.islami.ui.data_models.RadiosItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadioViewModel @Inject constructor(private val useCase: RadioUseCase) : ViewModel() {
    val radioList = MutableLiveData<List<RadiosItem?>?>()

    fun getRadiosList() {
        viewModelScope.launch {
            val response =useCase.execute()
            radioList.value = response.radios
        }
    }

}