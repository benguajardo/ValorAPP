package com.example.valorapp

import ItemViewModel
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class MatchfoundActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_matchfound)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MatchpvpActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        viewModel.items.observe(this, Observer { item ->
            val imageView1 = findViewById<ImageView>(R.id.agent1)
            val imageView2 = findViewById<ImageView>(R.id.agent2)
            val imageView3 = findViewById<ImageView>(R.id.agent3)
            val imageView4 = findViewById<ImageView>(R.id.agent4)
            val imageView5 = findViewById<ImageView>(R.id.agent5)
            Glide.with(this)
                .load(item.agent1)
                .into(imageView1)
            Glide.with(this)
                .load(item.agent2)
                .into(imageView2)
            Glide.with(this)
                .load(item.agent3)
                .into(imageView3)
            Glide.with(this)
                .load(item.agent4)
                .into(imageView4)
            Glide.with(this)
                .load(item.agent5)
                .into(imageView5)

        })
    }
}