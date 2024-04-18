package com.example.valorapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.valorapp.databinding.ActivityLoginDialogBinding

class LoginDialogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lottieImg.setAnimation(R.raw.ok)
        binding.lottieImg.playAnimation()
    }
}