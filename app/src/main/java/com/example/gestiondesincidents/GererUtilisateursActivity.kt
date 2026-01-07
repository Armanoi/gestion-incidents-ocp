package com.example.gestiondesincidents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GererUtilisateursActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var utilisateurAdapter: UtilisateurAdapter
    private lateinit var utilisateurList: ArrayList<Utilisateur>
    private lateinit var dbHelper: DBHelper
    private lateinit var btnAjouterUtilisateur: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerer_utilisateurs)

        recyclerView = findViewById(R.id.recyclerViewUtilisateurs)
        btnAjouterUtilisateur = findViewById(R.id.btnAjouterUtilisateur)
        dbHelper = DBHelper(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        utilisateurList = ArrayList()
        utilisateurAdapter = UtilisateurAdapter(utilisateurList, this)
        recyclerView.adapter = utilisateurAdapter

        try {
            loadUtilisateurs()
        } catch (e: Exception) {
            Log.e("GererUtilisateurs", "Erreur lors du chargement des utilisateurs", e)
        }

        btnAjouterUtilisateur.setOnClickListener {
            val intent = Intent(this, AjouterModifierUtilisateurActivity::class.java)
            startActivityForResult(intent, 1) // Request Code = 1
        }
    }

    private fun loadUtilisateurs() {
        utilisateurList.clear()
        utilisateurList.addAll(dbHelper.getAllUtilisateurs())
        utilisateurAdapter.notifyDataSetChanged()
        Log.d("GererUtilisateurs", "Nombre d'utilisateurs affichés: ${utilisateurList.size}")
    }

    override fun onResume() {
        super.onResume()
        try {
            loadUtilisateurs()
        } catch (e: Exception) {
            Log.e("GererUtilisateurs", "Erreur lors du rafraîchissement des utilisateurs", e)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadUtilisateurs()
        }
    }
}
