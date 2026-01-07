package com.example.gestiondesincidents

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val btnQuestion1: Button = findViewById(R.id.btnQuestion1)
        val btnQuestion2: Button = findViewById(R.id.btnQuestion2)

        btnQuestion1.setOnClickListener {
            Toast.makeText(this, "Incident bien envoyer", Toast.LENGTH_SHORT).show()
        }

        btnQuestion2.setOnClickListener {
            Toast.makeText(this, "Incident bien envoyer", Toast.LENGTH_SHORT).show()
        }

        val btnAutre: Button = findViewById(R.id.btnAutre)
        btnAutre.setOnClickListener {
            val intent = Intent(this, DemanderIncidentActivity::class.java)
            startActivity(intent)
        }
    }
}