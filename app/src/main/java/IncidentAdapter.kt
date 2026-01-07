package com.example.gestiondesincidents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IncidentAdapter(
    private var incidents: MutableList<Incident>,
    private val onDeleteClick: (Incident) -> Unit,
    private val onModifyClick: (Incident, Int) -> Unit
) : RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder>() {

    class IncidentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.tvIncidentDescription)
        val status: TextView = view.findViewById(R.id.tvIncidentStatus)
        val btnDelete: Button = view.findViewById(R.id.btnSupprimerIncident)
        val btnModify: Button = view.findViewById(R.id.btnModifierIncident)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incident, parent, false)
        return IncidentViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        val incident = incidents[position]
        holder.description.text = incident.description
        holder.status.text = String.format("Statut : %s", incident.status)

        holder.btnDelete.setOnClickListener {
            onDeleteClick(incident)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, incidents.size)
        }

        holder.btnModify.setOnClickListener {
            onModifyClick(incident, position)
        }
    }

    override fun getItemCount(): Int = incidents.size
}
