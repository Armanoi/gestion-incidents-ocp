// AjouterIncidentActivity.kt
package com.example.gestiondesincidents

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AjouterIncidentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_incident)

        val etDescription = findViewById<EditText>(R.id.etDescriptionIncident)
        val btnAjouterIncident = findViewById<Button>(R.id.btnAjouterIncident)
        val dbHelper = DBHelper(this)

        btnAjouterIncident.setOnClickListener {
            val description = etDescription.text.toString().trim()
            val userId = 1 // Remplace ça par l'ID du user connecté
            val status = "En attente"

            if (description.isNotEmpty()) {
                val success = dbHelper.insertIncident(userId, description, status)
                if (success) {
                    Toast.makeText(this, "Incident ajouté avec succès", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Description vide", Toast.LENGTH_SHORT).show()
            }
        }
    }
}