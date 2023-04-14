package com.sbz.todo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Splashscreen : AppCompatActivity() {
    private val LOADING_TIME = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        window.statusBarColor = getColor(R.color.blue_light)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@Splashscreen, MainActivity::class.java))
            finish()
        }, LOADING_TIME)
    }
}