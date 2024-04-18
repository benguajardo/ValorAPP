package com.example.valorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.valorapp.databinding.ActivityEndMatchDialogBinding

class EndMatchDialogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEndMatchDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndMatchDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lottieImg.setAnimation(R.raw.win)
        binding.lottieImg.playAnimation()
    }
}