package com.example.oilcollection.features

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.oilcollection.databinding.ActivityMainBinding
import com.example.oilcollection.firebase.Database
import com.example.oilcollection.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : BaseActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = Firebase.auth

        binding?.signUpButton?.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding?.loginButton?.setOnClickListener {
            email = binding?.etEmailLogin?.text.toString().trim { it <= ' ' }
            password = binding?.etPasswordLogin?.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                binding?.etEmailLogin?.error = "Name cannot be empty"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding?.etPasswordLogin?.error = "Password cannot be empty"
                return@setOnClickListener
            }
            signInRegisteredUser()
        }
    }

    private fun signInRegisteredUser() {
        binding?.ivMainImage?.visibility = View.INVISIBLE
        binding?.loginContainer?.visibility = View.INVISIBLE
        binding?.progressBar?.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                binding?.progressBar?.visibility = View.INVISIBLE
                if (task.isSuccessful) {
                    Database().signInUser(this@SignInActivity)
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Sign in", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    fun signInSuccessful(user: User) {
        binding?.progressBar?.visibility = View.INVISIBLE
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

}