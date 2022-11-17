package com.valentin.advancedcounter.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.valentin.advancedcounter.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity(R.layout.activity_splash_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        delayBeforeRedirect()
    }

    private fun delayBeforeRedirect() {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
                setSecondsToTv((p0 / 1000).toInt())
            }

            override fun onFinish() {
                goToMainActivity()
            }

        }.start()
    }

    private fun setSecondsToTv(secondsLeft: Int) {
        timeTv.text = secondsLeft.toString()
    }

    private fun goToMainActivity() {
        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }
}