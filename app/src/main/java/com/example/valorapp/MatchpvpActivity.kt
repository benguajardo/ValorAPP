package com.example.valorapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlin.random.Random

class MatchpvpActivity : AppCompatActivity() {
    private lateinit var team1Win: ImageView
    private lateinit var team2Win: ImageView
    private val handler = Handler(Looper.getMainLooper())
    private var toggle = true
    private var winningTeam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_matchpvp)

        team1Win = findViewById(R.id.Team1Win)
        team2Win = findViewById(R.id.Team2Win)

        val agent1 = intent.getStringExtra("agent1")
        val agent2 = intent.getStringExtra("agent2")
        val agent3 = intent.getStringExtra("agent3")
        val agent4 = intent.getStringExtra("agent4")
        val agent5 = intent.getStringExtra("agent5")

        val teamImage1 = intent.getStringExtra("teamImage1")
        val teamImage2 = intent.getStringExtra("teamImage2")
        val teamImage3 = intent.getStringExtra("teamImage3")
        val teamImage4 = intent.getStringExtra("teamImage4")
        val teamImage5 = intent.getStringExtra("teamImage5")

        loadAgentImage(agent1, findViewById(R.id.imageView1))
        loadAgentImage(agent2, findViewById(R.id.imageView2))
        loadAgentImage(agent3, findViewById(R.id.imageView3))
        loadAgentImage(agent4, findViewById(R.id.imageView4))
        loadAgentImage(agent5, findViewById(R.id.imageView5))

        loadAgentImage(teamImage1, findViewById(R.id.teamImage1))
        loadAgentImage(teamImage2, findViewById(R.id.teamImage2))
        loadAgentImage(teamImage3, findViewById(R.id.teamImage3))
        loadAgentImage(teamImage4, findViewById(R.id.teamImage4))
        loadAgentImage(teamImage5, findViewById(R.id.teamImage5))

        startToggleAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, EndmatchActivity::class.java).apply {
                putExtra("winningTeam", winningTeam)
            }
            startActivity(intent)
            finish()
        }, 5000)
    }

    private fun loadAgentImage(url: String?, imageView: ImageView) {
        if (url != null) {
            Glide.with(this)
                .load(url)
                .into(imageView)
        }
    }

    private fun startToggleAnimation() {
        val runnable = object : Runnable {
            override fun run() {
                if (toggle) {
                    team1Win.alpha = 1f
                    team2Win.alpha = 0f
                } else {
                    team1Win.alpha = 0f
                    team2Win.alpha = 1f
                }
                toggle = !toggle
                handler.postDelayed(this, 200) // Cambia cada 200 ms
            }
        }
        handler.post(runnable)

        // Parar la animación después de 2 segundos y elegir una aleatoriamente
        handler.postDelayed({
            handler.removeCallbacks(runnable)
            val randomTeam = Random.nextBoolean()
            winningTeam = if (randomTeam) {
                team1Win.alpha = 1f
                team2Win.alpha = 0f
                "1"
            } else {
                team1Win.alpha = 0f
                team2Win.alpha = 1f
                "2"
            }
        }, 3000) // Animación por 2 segundos
    }
}
