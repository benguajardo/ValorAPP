package com.example.valorapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btnViewQuickplay = findViewById<View>(R.id.btnViewQuickplay)
        btnViewQuickplay.setOnClickListener {
            val intent = Intent(this, QuickplayActivity::class.java)
            startActivity(intent)
        }

        val btnViewChangeTeam = findViewById<View>(R.id.btnViewChangeTeam)
        btnViewChangeTeam.setOnClickListener {
            val intent = Intent(this, ChangeteamActivity::class.java)
            startActivity(intent)
        }

        val btnViewProfile = findViewById<View>(R.id.btnViewProfile)
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
        mediaPlayer?.stop()
        if (mediaPlayer != null)
            position = mediaPlayer!!.currentPosition
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0

}