package com.healthypocket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.healthypocket.dto.MedicamentoDTO
import com.healthypocket.model.Medicamento

class MedicamentoViewModel : ViewModel(){
    val datamed = MutableLiveData<List<Medicamento>>(listOf())

    fun getmedicamentos(){
      val dto = MedicamentoDTO()

      dto.getMeds { data -> datamed.postValue(data) }
    }
}