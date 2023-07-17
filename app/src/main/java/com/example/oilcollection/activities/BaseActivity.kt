package com.example.oilcollection.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oilcollection.R
import com.google.firebase.auth.FirebaseAuth

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun getCurrentUserID(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}