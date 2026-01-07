package com.example.gestiondesincidents


data class Utilisateur(
    val id: Int,
    var nom: String,   // Doit être "var" pour être modifiable
    var prenom: String,
    var login: String,
    var password: String
)



