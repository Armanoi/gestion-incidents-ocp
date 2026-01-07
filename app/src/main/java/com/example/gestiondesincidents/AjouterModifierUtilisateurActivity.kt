package com.example.gestiondesincidents

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AjouterModifierUtilisateurActivity : AppCompatActivity() {

    private lateinit var etNom: EditText
    private lateinit var etPrenom: EditText
    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnValider: Button
    private lateinit var btnAnnuler: Button
    private lateinit var dbHelper: DBHelper

    private var utilisateurId: Int? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_modifier_utilisateur)

        dbHelper = DBHelper(this)

        // Initialisation des vues
        etNom = findViewById(R.id.etNom)
        etPrenom = findViewById(R.id.etPrenom)
        etLogin = findViewById(R.id.etLogin)
        etPassword = findViewById(R.id.etPassword)
        btnValider = findViewById(R.id.btnValider)
        btnAnnuler = findViewById(R.id.btnAnnuler)

        // Vérifier si on est en mode modification
        val intent = intent
        if (intent.hasExtra("id")) {
            utilisateurId = intent.getIntExtra("id", -1)
            etNom.setText(intent.getStringExtra("nom"))
            etPrenom.setText(intent.getStringExtra("prenom"))
            etLogin.setText(intent.getStringExtra("login"))
            etPassword.setText(intent.getStringExtra("password"))
        }

        // Gestion du bouton Valider
        btnValider.setOnClickListener {
            val nom = etNom.text.toString().trim()
            val prenom = etPrenom.text.toString().trim()
            val login = etLogin.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nom.isEmpty() || prenom.isEmpty() || login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (utilisateurId == null) {
                // Mode ajout
                val isAdded = dbHelper.ajouterUtilisateur(nom, prenom, login, password)
                if (isAdded) {
                    Toast.makeText(this, "Utilisateur ajouté avec succès", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Mode modification
                val isUpdated = dbHelper.modifierUtilisateur(utilisateurId!!, nom, prenom, login, password)
                if (isUpdated) {
                    Toast.makeText(this, "Utilisateur modifié avec succès", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erreur lors de la modification", Toast.LENGTH_SHORT).show()
                }
            }

            finish() // Fermer l'activité après l'ajout ou la modification
        }

        // Gestion du bouton Annuler
        btnAnnuler.setOnClickListener {
            finish() // Fermer l'activité sans rien enregistrer
        }
    }
}
