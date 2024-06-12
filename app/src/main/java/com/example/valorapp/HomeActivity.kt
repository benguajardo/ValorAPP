package com.example.valorapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var position: Int = 0
    private lateinit var textWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Inicializar las vistas
        textWelcome = findViewById(R.id.textWelcome)
        val btnViewQuickplay = findViewById<View>(R.id.btnViewQuickplay)
        val btnViewProfile = findViewById<View>(R.id.btnViewProfile)

        // Configurar los listeners de los botones
        btnViewQuickplay.setOnClickListener {
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

        // Actualizar el mensaje de bienvenida con el correo electrónico del usuario
        updateWelcomeMessage()
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

    private fun updateWelcomeMessage() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let {
            val email = currentUser.email
            if (!email.isNullOrEmpty()) {
                textWelcome.text = "Bienvenid@ $email"
            }
        }
    }
}
