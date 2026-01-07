package com.example.gestiondesincidents


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val authManager = AuthManager(this)


        val sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isAdminLoggedIn", true)
        editor.apply()


        loginButton.setOnClickListener {
            val role = authManager.login(emailInput.text.toString(), passwordInput.text.toString())
            if (role != null) {
                Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, UserActivity::class.java))

            } else {
                Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}