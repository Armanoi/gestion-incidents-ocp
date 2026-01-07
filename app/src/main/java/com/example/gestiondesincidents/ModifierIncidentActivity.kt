package com.example.gestiondesincidents

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ModifierIncidentActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifier_incident)

        val etDescription = findViewById<EditText>(R.id.etModifierDescription)
        val etStatus = findViewById<EditText>(R.id.spinnerStatus)
        val btnModifier = findViewById<Button>(R.id.btnModifierIncident)
        val btnSupprimer = findViewById<Button>(R.id.btnSupprimerIncident)
        val dbHelper = DBHelper(this)

        val incidentId = intent.getLongExtra("incident_id", -1)

        btnModifier.setOnClickListener {
            val description = etDescription.text.toString().trim()
            val status = etStatus.text.toString().trim()

            if (description.isNotEmpty() && status.isNotEmpty()) {
                val success = dbHelper.modifierIncident(incidentId.toInt(), description, status)
                if (success) {
                    Toast.makeText(this, "Incident modifié avec succès", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Erreur lors de la modification", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }

        btnSupprimer.setOnClickListener {
            val success = dbHelper.supprimerIncident(incidentId.toInt())
            if (success) {
                Toast.makeText(this, "Incident supprimé", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erreur lors de la suppression", Toast.LENGTH_SHORT).show()
            }
        }
    }
}