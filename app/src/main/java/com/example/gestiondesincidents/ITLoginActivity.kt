package com.example.gestiondesincidents



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ItLoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itlogin)

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val authManager = AuthManager(this)

        loginButton.setOnClickListener {
            val role = authManager.login(emailInput.text.toString(), passwordInput.text.toString())
            if (role != null) {
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ITActivity::class.java))
            } else {
                Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
