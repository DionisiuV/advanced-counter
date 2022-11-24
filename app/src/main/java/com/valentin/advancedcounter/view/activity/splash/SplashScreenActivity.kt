package com.valentin.advancedcounter.view.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.valentin.advancedcounter.R
import com.valentin.advancedcounter.view.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity(R.layout.activity_splash_screen) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        countDownBeforeRedirect()
    }

    private fun countDownBeforeRedirect() {
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