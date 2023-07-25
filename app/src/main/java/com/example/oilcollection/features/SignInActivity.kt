package com.example.oilcollection.features

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.oilcollection.databinding.ActivityMainBinding
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
            loginRegisteredUser()
        }
    }

    private fun loginRegisteredUser() {
        binding?.ivMainImage?.isInvisible
        binding?.loginContainer?.isInvisible
        binding?.progressBar?.isVisible
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                binding?.progressBar?.isGone
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Sign in", "signInWithEmail:success")
//                    val user = auth.currentUser
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

}