package com.example.gestiondesincidents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation des autres boutons
        val userButton: Button = findViewById(R.id.buttonUser)
        val itButton: Button = findViewById(R.id.buttonIT)
        val adminButton: Button = findViewById(R.id.buttonAdmin)
        val textViewMoreInfo = findViewById<TextView>(R.id.textViewMoreInfo)

        // Redirection pour le bouton "User"
        userButton.setOnClickListener {
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
        }

        // Redirection pour le bouton "IT"
        itButton.setOnClickListener {
            val intent = Intent(this, ItLoginActivity::class.java)
            startActivity(intent)
        }

        // Redirection pour le bouton "Admin"
        adminButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Redirection pour "En savoir plus"
        textViewMoreInfo.setOnClickListener {
            val intent = Intent(this, AboutOcpActivity::class.java)
            startActivity(intent)
        }

        // ➡️ Nouveau texte pour réinitialisation
        val resetPasswordTextView: TextView = findViewById(R.id.resetPasswordTextView)
        resetPasswordTextView.setOnClickListener {
            val intent = Intent(this, ReinitialisationMtp::class.java)
            startActivity(intent)
        }
    }
}