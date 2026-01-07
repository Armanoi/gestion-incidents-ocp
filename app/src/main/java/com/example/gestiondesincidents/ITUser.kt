package com.example.gestiondesincidents

import java.io.Serializable

data class ITUser(
    val id: Int,
    var nom: String,
    var prenom: String,
    var login: String,
    var password: String
) : Serializable

