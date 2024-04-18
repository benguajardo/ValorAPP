package com.example.valorapp
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btnViewRegister = findViewById<View>(R.id.btnViewRegister)
        btnViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnViewHome = findViewById<View>(R.id.btnViewHome)
        btnViewHome.setOnClickListener {
            val intent = Intent(this, LoginDialogActivity::class.java)
            startActivity(intent)
            Handler(Looper.getMainLooper()).postDelayed({
                val siguienteIntent = Intent(this, HomeActivity::class.java)
                startActivity(siguienteIntent)
            }, 1000)
        }

    }
}