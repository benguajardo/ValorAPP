package com.example.valorapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class EditprofileActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editprofile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnViewProfile = findViewById<View>(R.id.btnViewProfile)
        btnViewProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        val btnGuardarCambios = findViewById<View>(R.id.btnGuardarCambios)
        btnGuardarCambios.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
            Handler(Looper.getMainLooper()).postDelayed({
                val siguienteIntent = Intent(this, ProfileActivity::class.java)
                startActivity(siguienteIntent)
            }, 1000)
        }

    }
}