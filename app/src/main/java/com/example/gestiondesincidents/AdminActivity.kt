package com.example.gestiondesincidents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        // Configuration de la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Masquer le titre
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Activer l'icône de retour

        toolbar.setNavigationOnClickListener {
            finish() // Fermer l'activité pour revenir à la page précédente
        }

        // Vérification de la session Admin
        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val isAdminLoggedIn = sharedPreferences.getBoolean("isAdminLoggedIn", false)

        if (!isAdminLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val dbHelper = DBHelper(this)
        dbHelper.ajouterAdmin()
        Toast.makeText(this, "Admin ajouté avec succès !", Toast.LENGTH_SHORT).show()

        val btnGererIT: Button = findViewById(R.id.btnGererIT)
        btnGererIT.setOnClickListener {
            val intent = Intent(this, GererItActivity::class.java)
            startActivity(intent)
        }

        try {
            val btnManageUsers: Button = findViewById(R.id.btnGererUtilisateurs)
            val btnManageIncidents: Button = findViewById(R.id.btnGererIncidents)

            btnManageUsers.setOnClickListener {
                val intent = Intent(this, GererUtilisateursActivity::class.java)
                startActivity(intent)
            }

            btnManageIncidents.setOnClickListener {
                val intent = Intent(this, GererIncidentsActivity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}