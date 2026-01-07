package com.example.gestiondesincidents



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ITActivity : AppCompatActivity() {



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itactivity)

        val btnDemander: Button = findViewById(R.id.btnDemander)
        btnDemander.setOnClickListener {
            val intent = Intent(this, GererIncidentsActivity::class.java)
            startActivity(intent)
        }
    }
}