package com.example.gestiondesincidents

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GererIncidentsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAjouterIncident: Button
    private lateinit var incidentList: MutableList<Incident>
    private lateinit var incidentAdapter: IncidentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerer_incidents)

        recyclerView = findViewById(R.id.recyclerIncidents)
        btnAjouterIncident = findViewById(R.id.btnAjouterIncident)

        incidentList = mutableListOf(
            Incident(1, 101, "Problème réseau", "En attente"),
            Incident(2, 102, "Bug application", "Résolu")
        )

        incidentAdapter = IncidentAdapter(incidentList,
            onDeleteClick = { incident ->
                incidentList.remove(incident)
                incidentAdapter.notifyDataSetChanged()
            },
            onModifyClick = { incident, position ->
                showModifyDialog(incident, position)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = incidentAdapter

        btnAjouterIncident.setOnClickListener {
            showAddDialog() // Hna kansayft dialog bach ndir l'ajout
        }

    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_incident, null)
        val editDescription = dialogView.findViewById<EditText>(R.id.editIncidentDescription)
        val editStatus = dialogView.findViewById<EditText>(R.id.editIncidentStatus)

        AlertDialog.Builder(this)
            .setTitle("Ajouter un Incident")
            .setView(dialogView)
            .setPositiveButton("Ajouter") { _, _ ->
                val description = editDescription.text.toString().trim()
                val status = editStatus.text.toString().trim()

                if (description.isNotEmpty() && status.isNotEmpty()) {
                    val newIncident = Incident(
                        id = incidentList.size + 1,
                        userId = 103, //📌 Tu peux changer ça dynamiquement
                        description = description,
                        status = status
                    )
                    incidentList.add(newIncident)
                    incidentAdapter.notifyItemInserted(incidentList.size - 1) //📌 Rafraîchir la liste après ajout
                } else {
                    Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }


    private fun showModifyDialog(incident: Incident, position: Int) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_incident, null)
        val editDescription = dialogView.findViewById<EditText>(R.id.editIncidentDescription)
        val editStatus = dialogView.findViewById<EditText>(R.id.editIncidentStatus)

        editDescription.setText(incident.description)
        editStatus.setText(incident.status)

        AlertDialog.Builder(this)
            .setTitle("Modifier Incident")
            .setView(dialogView)
            .setPositiveButton("Modifier") { _, _ ->
                val newDescription = editDescription.text.toString()
                val newStatus = editStatus.text.toString()
                if (newDescription.isNotEmpty() && newStatus.isNotEmpty()) {
                    incidentList[position] = Incident(incident.id, incident.userId, newDescription, newStatus)
                    incidentAdapter.notifyItemChanged(position)
                }
            }
            .setNegativeButton("Annuler", null)
            .show()
    }
}
