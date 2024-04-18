package com.example.valorapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuickplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quickplay)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnViewHome = findViewById<View>(R.id.btnViewHome)
        btnViewHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val btnViewMatchFound = findViewById<View>(R.id.btnViewMatchFound)
        btnViewMatchFound.setOnClickListener {
            val intent = Intent(this, MatchfoundActivity::class.java)
            startActivity(intent)
        }

        val btnViewChangeTeam = findViewById<View>(R.id.btnViewChangeTeam)
        btnViewChangeTeam.setOnClickListener {
            val intent = Intent(this, ChangeteamActivity::class.java)
            startActivity(intent)
        }
    }
}