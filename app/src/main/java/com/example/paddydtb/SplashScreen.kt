package com.example.paddydtb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val mainIntent = Intent(this@SplashScreen, LoginActivity::class.java)
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(mainIntent)
        finish()
    }
}
