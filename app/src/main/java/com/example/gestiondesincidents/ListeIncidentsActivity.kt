package com.example.gestiondesincidents

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity

class ListeIncidentsActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private var cursor: Cursor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_incidents)

        val listView = findViewById<ListView>(R.id.recyclerViewIncidents)
        dbHelper = DBHelper(this)
        cursor = dbHelper.getAllIncidents()

        if (cursor != null && cursor!!.count > 0) {
            val adapter = SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                arrayOf("description", "status"),
                intArrayOf(android.R.id.text1, android.R.id.text2),
                0
            )
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, _, id ->
                val intent = Intent(this, ModifierIncidentActivity::class.java)
                intent.putExtra("incident_id", id)
                startActivity(intent)
            }
        } else {
            // Si cursor kayn null ola mavi, mataffichiw incidents
            cursor?.close()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cursor?.close()
        dbHelper.close()
    }
}
