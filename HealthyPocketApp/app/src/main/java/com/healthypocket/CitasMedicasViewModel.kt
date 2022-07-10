package com.healthypocket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healthypocket.dto.CitasDTO
import com.healthypocket.model.CitaMedica

class CitasMedicasViewModel : ViewModel(){

    val datacita = MutableLiveData<List<CitaMedica>>(listOf())

    fun getcitasmedicas(){
        val dto = CitasDTO()

        dto.getCitas { data -> datacita.postValue(data) }
    }
}