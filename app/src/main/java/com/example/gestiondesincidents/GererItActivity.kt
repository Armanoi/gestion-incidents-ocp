package com.example.gestiondesincidents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GererItActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itAdapter: ITAdapter
    private val itList = mutableListOf<ITUser>()
    private lateinit var dbHelper: DBHelper

    // Launcher pour gérer le résultat de l'activité AjouterModifierITActivity
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            Log.d("GererItActivity", "Résultat OK, recharge des utilisateurs.")
            loadITUsers() // Recharge la liste des utilisateurs
        } else {
            Log.d("GererItActivity", "Résultat annulé ou erreur.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerer_it)

        // Initialisation de la base de données et des composants
        dbHelper = DBHelper(this)
        recyclerView = findViewById(R.id.recyclerIT)
        val btnAjouterIT: Button = findViewById(R.id.btnAjouterIT)

        // Configuration de RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        itAdapter = ITAdapter(this, itList,
            onDeleteClick = { itUser -> supprimerIT(itUser) },
            onEditClick = { itUser ->
                val intent = Intent(this, AjouterModifierITActivity::class.java)
                intent.putExtra("itUser", itUser) // Passe l'utilisateur IT à éditer
                launcher.launch(intent)
            }
        )
        recyclerView.adapter = itAdapter

        // Charger la liste des utilisateurs IT
        loadITUsers()

        // Gestion du bouton Ajouter IT
        btnAjouterIT.setOnClickListener {
            Log.d("GererItActivity", "Bouton Ajouter IT cliqué.")
            val intent = Intent(this, AjouterModifierITActivity::class.java)
            launcher.launch(intent)
        }
    }

    private fun loadITUsers() {
        Log.d("GererItActivity", "Chargement des utilisateurs IT.")
        itList.clear() // Vide la liste avant de charger
        val users = dbHelper.getAllITUsers() // Récupère les utilisateurs depuis la base de données
        Log.d("GererItActivity", "Utilisateurs récupérés : $users")
        itList.addAll(users) // Ajoute les utilisateurs récupérés
        itAdapter.notifyDataSetChanged() // Notifie l'adaptateur pour rafraîchir la RecyclerView
    }

    private fun supprimerIT(itUser: ITUser) {
        Log.d("GererItActivity", "Suppression de l'utilisateur IT avec ID : ${itUser.id}")
        if (dbHelper.deleteITUser(itUser.id)) {
            loadITUsers() // Recharge la liste après suppression
            Toast.makeText(this, "Utilisateur IT supprimé avec succès", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Échec de la suppression de l'utilisateur IT", Toast.LENGTH_SHORT).show()
        }
    }
}