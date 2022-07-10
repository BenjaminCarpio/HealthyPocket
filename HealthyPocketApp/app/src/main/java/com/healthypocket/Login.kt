package com.healthypocket

import android.view.View
import com.healthypocket.databinding.FragmentIniciarSesionBinding
import com.healthypocket.dto.UserDTO
import com.healthypocket.model.User

class Login(view: View) {

    val binding = FragmentIniciarSesionBinding.bind(view)
    val user = UserDTO()

    private var _id: String? = null
    private var username: String? = null
    private var password: String? = null

    fun verificarLogin(user: User){
        username =  binding.etUsernameLogin.text.toString()
        password =  binding.etPasswordLogin.text.toString()
    }
}