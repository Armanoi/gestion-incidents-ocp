package com.example.gestiondesincidents

data class Incident(
    val id: Int,
    val userId: Int,
    val description: String,
    val status: String = "En attente" // Ajout d'un statut par défaut
)
