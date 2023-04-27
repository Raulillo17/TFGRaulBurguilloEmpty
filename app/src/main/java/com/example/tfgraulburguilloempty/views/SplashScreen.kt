package com.example.tfgraulburguilloempty.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.tfgraulburguilloempty.R

class SplashScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val backgroundImg : ImageView = findViewById(R.id.iv_logo)
        val backgroundtext : TextView  = findViewById(R.id.tvBienvenidos)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)
        backgroundImg.startAnimation(sideAnimation)
        backgroundtext.startAnimation(sideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }, 3000)
    }
}