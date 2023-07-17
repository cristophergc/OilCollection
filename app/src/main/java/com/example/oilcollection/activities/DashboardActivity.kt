package com.example.oilcollection.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oilcollection.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private var binding: ActivityDashboardBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarDashboard)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding?.toolbarDashboard?.setNavigationOnClickListener { handleBackPressed() }

    }

    private fun handleBackPressed(){
        finish()
    }
}