package com.example.oilcollection.features

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.oilcollection.R
import com.example.oilcollection.firebase.Database
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_TIMEOUT = 2500L

    private lateinit var splashJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        splashJob = CoroutineScope(Dispatchers.Main).launch {
            val currentUserId = Database().getCurrentUserId()
            delay(SPLASH_SCREEN_TIMEOUT)
            if (currentUserId.isNotEmpty()) {
                startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
            }
            finish()
        }
    }

    override fun onDestroy() {
        splashJob.cancel() // Cancel the coroutine job to avoid leaks
        super.onDestroy()
    }
}