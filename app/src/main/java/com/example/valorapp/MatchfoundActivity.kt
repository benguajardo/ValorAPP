package com.example.valorapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MatchfoundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_matchfound)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MatchpvpActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}