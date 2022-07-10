package com.healthypocket.model

data class User(
    var _id: String? = null,
    var username: String? = null,
    var email: String? = null,
    var name: String? = null,
    var lastname: String? = null,
    var password: String? = null,
    var direccion: String? = null,
    var telefono: String? = null,
    var fechaNacimiento: String? = null
)
