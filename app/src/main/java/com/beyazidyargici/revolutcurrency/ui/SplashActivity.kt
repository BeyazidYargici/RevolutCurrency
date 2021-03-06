package com.beyazidyargici.revolutcurrency.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.beyazidyargici.revolutcurrency.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val anim : Animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        ac_splash_logo.startAnimation(anim)

        // Open [MainActivity] after 3 second
        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        },3000)
    }
}
