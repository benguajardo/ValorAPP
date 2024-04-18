package com.example.valorapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MatchpvpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_matchpvp)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, EndmatchActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}