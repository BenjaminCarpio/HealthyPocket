package com.healthypocket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healthypocket.dto.ExamenDTO
import com.healthypocket.model.Examen

class ExamenViewModel : ViewModel() {
    val dataexm = MutableLiveData<List<Examen>>(listOf())

    fun getExamenes(){
        val dto = ExamenDTO()

        dto.getExamenes { data -> dataexm.postValue(data) }
    }
}