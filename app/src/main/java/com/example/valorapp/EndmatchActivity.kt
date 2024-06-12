package com.example.valorapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class EndmatchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_endmatch)

        val winningTeam = intent.getStringExtra("winningTeam")
        Log.d("Equipo Ganador", "$winningTeam")

        val imageView = findViewById<ImageView>(R.id.winningTeamImageView)
        val team1ImageRes = R.drawable.ic_valorant_victory  // Reemplaza con la URL de tu imagen para el equipo 1
        val team2ImageRes = R.drawable.ic_valorant_defeat  // Reemplaza con la URL de tu imagen para el equipo 2

        if (winningTeam == "1") {
            imageView.setImageResource(team1ImageRes)
            val intent = Intent(this, EndMatchDialogActivity::class.java)
            startActivity(intent)
        } else {
            imageView.setImageResource(team2ImageRes)
        }



        val btnViewHome = findViewById<View>(R.id.btnViewHome)
        btnViewHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadImage(url: String, imageView: ImageView) {
        Glide.with(this)
            .load(url)
            .into(imageView)
    }
}
