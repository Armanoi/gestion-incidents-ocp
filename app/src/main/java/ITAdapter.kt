package com.example.gestiondesincidents



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ITAdapter(
    private val context: Context,
    private val itList: List<ITUser>,
    private val onDeleteClick: (ITUser) -> Unit,
    private val onEditClick: (ITUser) -> Unit
) : RecyclerView.Adapter<ITAdapter.ITViewHolder>() {

    inner class ITViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNom: TextView = itemView.findViewById(R.id.tvNom)
        val txtPrenom: TextView = itemView.findViewById(R.id.tvPrenom)
        val btnDelete: Button = itemView.findViewById(R.id.btnSupprimer) // Changement de ImageButton à Button
        val btnEdit: Button = itemView.findViewById(R.id.btnModifier) // Changement de ImageButton à Button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ITViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_it_user, parent, false)
        return ITViewHolder(view)
    }

    override fun onBindViewHolder(holder: ITViewHolder, position: Int) {
        val user = itList[position]
        holder.txtNom.text = user.nom
        holder.txtPrenom.text = user.prenom
        holder.btnDelete.setOnClickListener { onDeleteClick(user) }
        holder.btnEdit.setOnClickListener { onEditClick(user) }
    }

    override fun getItemCount(): Int = itList.size
}