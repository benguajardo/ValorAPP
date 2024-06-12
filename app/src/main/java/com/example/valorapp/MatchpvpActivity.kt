package com.example.valorapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

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


        val agent1 = intent.getStringExtra("agent1")
        val agent2 = intent.getStringExtra("agent2")
        val agent3 = intent.getStringExtra("agent3")
        val agent4 = intent.getStringExtra("agent4")
        val agent5 = intent.getStringExtra("agent5")

        loadAgentImage(agent1, findViewById(R.id.imageView1))
        loadAgentImage(agent2, findViewById(R.id.imageView2))
        loadAgentImage(agent3, findViewById(R.id.imageView3))
        loadAgentImage(agent4, findViewById(R.id.imageView4))
        loadAgentImage(agent5, findViewById(R.id.imageView5))
    }

    private fun loadAgentImage(url: String?, imageView: ImageView) {
        if (url != null) {
            Glide.with(this)
                .load(url)
                .into(imageView)
        }
    }
}