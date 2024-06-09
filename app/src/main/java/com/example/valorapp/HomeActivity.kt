package com.example.valorapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // Asegúrate de que el archivo XML se llama activity_home

        // Inicializa las vistas
        val btnViewQuickplay = findViewById<View>(R.id.btnViewQuickplay)
        val btnViewChangeTeam = findViewById<View>(R.id.btnViewChangeTeam)
        val btnViewProfile = findViewById<View>(R.id.btnViewProfile)

        // Configura los listeners
        btnViewQuickplay.setOnClickListener {
            val intent = Intent(this, QuickplayActivity::class.java)
            startActivity(intent)
        }

        btnViewChangeTeam.setOnClickListener {
            val intent = Intent(this, ChangeteamActivity::class.java)
            startActivity(intent)
        }

        btnViewProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        mediaPlayer = MediaPlayer.create(this, R.raw.die_for_you)
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.seekTo(position)
        mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
        position = mediaPlayer?.currentPosition ?: 0
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
