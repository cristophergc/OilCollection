package com.example.oilcollection.activities

import android.content.Intent
import android.os.Bundle
import com.example.oilcollection.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.signUpButton?.setOnClickListener {
            val intent = Intent(this, NewCustomerFormActivity::class.java)
            startActivity(intent)
        }

        binding?.loginButton?.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }


}