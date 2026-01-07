package com.example.gestiondesincidents




import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DemanderIncidentActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demander_incident)

        val etTitre = findViewById<EditText>(R.id.etTitreIncident)
        val etDescription = findViewById<EditText>(R.id.etDescriptionIncident)
        val btnEnvoyer = findViewById<Button>(R.id.btnEnvoyerIncident)

        val dbHelper = DBHelper(this)

        btnEnvoyer.setOnClickListener {
            val titre = etTitre.text.toString().trim()
            val description = etDescription.text.toString().trim()

            if (titre.isNotEmpty() && description.isNotEmpty()) {
                val success = dbHelper.ajouterIncident(titre,
                    description)

                if (success) {
                    Toast.makeText(this, "Incident enregistré avec succès", Toast.LENGTH_LONG).show()
                    etTitre.text.clear()
                    etDescription.text.clear()
                } else {
                    Toast.makeText(this, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}