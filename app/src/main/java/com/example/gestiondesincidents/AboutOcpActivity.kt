package com.example.gestiondesincidents

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AboutOcpActivity : AppCompatActivity() {
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_ocp)

        // Toolbar setup
        val toolbar = findViewById<Toolbar>(R.id.toolbarAbout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Expandable text setup
        val textMore = findViewById<TextView>(R.id.textViewMore)
        val toggleText = findViewById<TextView>(R.id.textViewToggle)

        toggleText.setOnClickListener {
            if (isExpanded) {
                textMore.visibility = View.GONE
                toggleText.text = getString(R.string.read_more)
            } else {
                textMore.visibility = View.VISIBLE
                toggleText.text = getString(R.string.reduce)
            }
            isExpanded = !isExpanded
        }

        // Transition animations
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}