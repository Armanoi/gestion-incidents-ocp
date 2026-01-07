package com.example.gestiondesincidents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AjouterModifierITActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private var itUser: ITUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_modifier_itactivity)

        dbHelper = DBHelper(this)

        val btnEnregistrer: Button = findViewById(R.id.btnValider)
        val edtNom: EditText = findViewById(R.id.etNom)
        val edtPrenom: EditText = findViewById(R.id.etPrenom)
        val edtLogin: EditText = findViewById(R.id.etLogin)
        val edtPassword: EditText = findViewById(R.id.etPassword)

        itUser = intent.getSerializableExtra("itUser") as? ITUser
        itUser?.let {
            edtNom.setText(it.nom)
            edtPrenom.setText(it.prenom)
            edtLogin.setText(it.login)
            edtPassword.setText(it.password)
        }


        btnEnregistrer.setOnClickListener {
            Log.d("AjouterModifierITActivity", "Bouton Valider cliqué.")

            val nom = edtNom.text.toString()
            val prenom = edtPrenom.text.toString()
            val login = edtLogin.text.toString()
            val password = edtPassword.text.toString()

            Log.d("AjouterModifierITActivity", "Données : Nom=$nom, Prénom=$prenom, Login=$login, Password=$password")

            if (nom.isEmpty() || prenom.isEmpty() || login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = ITUser(itUser?.id ?: 0, nom, prenom, login, password)
            val result = if (itUser == null) dbHelper.addITUser(user) else dbHelper.updateITUser(user)

            Log.d("AjouterModifierITActivity", "Résultat opération : $result")

            if (result) {
                Toast.makeText(this, "Opération réussie", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            } else {
                Toast.makeText(this, "Erreur lors de l'opération", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
