package com.example.gestiondesincidents

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UtilisateurAdapter(
    private var utilisateurs: MutableList<Utilisateur>,  // Liste modifiable
    private val context: Context
) : RecyclerView.Adapter<UtilisateurAdapter.UtilisateurViewHolder>() {

    // ViewHolder pour chaque élément utilisateur
    class UtilisateurViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nom: TextView = view.findViewById(R.id.tvNom)
        val prenom: TextView = view.findViewById(R.id.tvPrenom)
        val btnModifier: Button = view.findViewById(R.id.btnModifier)
        val btnSupprimer: Button = view.findViewById(R.id.btnSupprimer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilisateurViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_utilisateur, parent, false)
        return UtilisateurViewHolder(view)
    }

    override fun onBindViewHolder(holder: UtilisateurViewHolder, position: Int) {
        if (position >= utilisateurs.size) {
            Log.e("UtilisateurAdapter", "Index hors limites : $position, taille: ${utilisateurs.size}")
            return
        }

        val utilisateur = utilisateurs[position]
        holder.nom.text = utilisateur.nom
        holder.prenom.text = utilisateur.prenom

        // Bouton Modifier
        holder.btnModifier.setOnClickListener {
            val intent = Intent(context, AjouterModifierUtilisateurActivity::class.java).apply {
                putExtra("id", utilisateur.id)
                putExtra("nom", utilisateur.nom)
                putExtra("prenom", utilisateur.prenom)
                putExtra("login", utilisateur.login)
                putExtra("password", utilisateur.password)
            }
            context.startActivity(intent)
        }

        // Bouton Supprimer
        holder.btnSupprimer.setOnClickListener {
            val dbHelper = DBHelper(context)
            dbHelper.supprimerUtilisateur(utilisateur.id)
            dbHelper.close()

            // Suppression de l'élément et mise à jour de la liste
            utilisateurs.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, utilisateurs.size)
        }
    }

    override fun getItemCount(): Int {
        return utilisateurs.size
    }

    // Fonction pour mettre à jour la liste des utilisateurs
    fun updateList(newList: List<Utilisateur>) {
        utilisateurs.clear()
        utilisateurs.addAll(newList)
        notifyDataSetChanged()
    }
}
