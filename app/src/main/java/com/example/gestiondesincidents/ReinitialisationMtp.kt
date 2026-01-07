package com.example.gestiondesincidents


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReinitialisationMtp : AppCompatActivity() {

    private lateinit var matriculeEditText: EditText
    private lateinit var nouveauMotDePasseEditText: EditText
    private lateinit var confirmerMotDePasseEditText: EditText
    private lateinit var confirmerButton: Button
    private lateinit var retourTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reinitialisation_mtp)

        // Initialisation des vues avec les IDs du layout
        matriculeEditText = findViewById(R.id.matriculeEditText)
        nouveauMotDePasseEditText = findViewById(R.id.nouveauMotDePasseEditText)
        confirmerMotDePasseEditText = findViewById(R.id.confirmerMotDePasseEditText)
        confirmerButton = findViewById(R.id.confirmerButton)
        retourTextView = findViewById(R.id.retourTextView)

        // Bouton de confirmation
        confirmerButton.setOnClickListener {
            val matricule = matriculeEditText.text.toString()
            val nouveauMotDePasse = nouveauMotDePasseEditText.text.toString()
            val confirmerMotDePasse = confirmerMotDePasseEditText.text.toString()

            when {
                matricule.isEmpty() || nouveauMotDePasse.isEmpty() || confirmerMotDePasse.isEmpty() ->
                    Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                nouveauMotDePasse != confirmerMotDePasse ->
                    Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show()
                else -> {
                    // Logique pour réinitialiser le mot de passe
                    Toast.makeText(this, "Mot de passe réinitialisé avec succès", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Lien de retour
        retourTextView.setOnClickListener {
            finish() // Ferme l'activité actuelle et revient à la précédente
        }
    }
}