package com.example.oilcollection.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.oilcollection.databinding.ActivityNewCustomerFormBinding

class NewCustomerFormActivity : AppCompatActivity() {

    private var binding: ActivityNewCustomerFormBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCustomerFormBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarNewCustomerForm)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarNewCustomerForm?.setNavigationOnClickListener { handleBackPressed() }
    }

    private fun handleBackPressed(){
        finish()
    }
}