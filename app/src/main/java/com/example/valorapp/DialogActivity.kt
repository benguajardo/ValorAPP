package com.example.valorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.valorapp.databinding.ActivityDialogBinding


class DialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialogBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lottieImg.setAnimation(R.raw.save)
        binding.lottieImg.playAnimation()
    }
}