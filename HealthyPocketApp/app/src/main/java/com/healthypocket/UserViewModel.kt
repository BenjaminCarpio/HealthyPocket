package com.healthypocket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.healthypocket.dto.MedicamentoDTO
import com.healthypocket.dto.UserDTO
import com.healthypocket.model.Medicamento
import com.healthypocket.model.User
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){
    val dataUser = MutableLiveData<User>(User(
        _id="",
    "",
        "",
        "",
        "",
        ""
    ))

    fun getSingleUser(userID:String){
        val dto = UserDTO()
        viewModelScope.launch {
            val user = dto.getSingleUser(userID)
            dataUser.postValue(user)
        }
    }
}