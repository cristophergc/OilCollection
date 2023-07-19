package com.example.oilcollection.activities

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.oilcollection.databinding.ActivityNewCustomerFormBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class NewCustomerFormActivity : AppCompatActivity() {

    private var binding: ActivityNewCustomerFormBinding? = null
    private var firstErrorEditText: EditText? = null

    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCustomerFormBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarNewCustomerForm)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarNewCustomerForm?.setNavigationOnClickListener { handleBackPressed() }

        binding?.signUpButton?.setOnClickListener {
            checkInputFieldError()
            registerUser()
        }
    }

    private fun checkInputFieldError() {
        val name = binding?.etNameSignUp?.text.toString().trimEnd { it <= ' ' }
        email = binding?.etEmailSignUp?.text.toString().trimEnd { it <= ' ' }
        val address = binding?.etAddressSignUp?.text.toString().trimEnd { it <= ' ' }
        val suburb = binding?.etSuburbSignUp?.text.toString().trimEnd { it <= ' ' }
        val city = binding?.etCitySignUp?.text.toString().trimEnd { it <= ' ' }
        val postCode = binding?.etPostCodeSignUp?.text.toString().trimEnd { it <= ' ' }
        val phone = binding?.etPhoneSignUp?.text.toString().trim { it <= ' ' }
        val contactPerson = binding?.etContactSignUp?.text.toString().trimEnd { it <= ' ' }
        val mobileNumber = binding?.etMobileNumberSignUp?.text.toString().trim { it <= ' ' }
        val bankAccName = binding?.etBankAccNameSignUp?.text.toString().trimEnd { it <= ' ' }
        val bankAccNumber = binding?.etBankAccNumberSignUp?.text.toString().trimEnd { it <= ' ' }
        password = binding?.etPasswordSignUp?.text.toString().trim { it <= ' ' }
        val confirmPassword = binding?.etConfirmPasswordSignUp?.text.toString().trim { it <= ' ' }

        val message = "This field cannot be empty"

        when {
            name.isEmpty() -> {
                binding?.etNameSignUp?.error = message
                firstErrorEditText = binding?.etNameSignUp
            }

            address.isEmpty() -> {
                binding?.etAddressSignUp?.error = message
                firstErrorEditText = binding?.etAddressSignUp
            }

            suburb.isEmpty() -> {
                binding?.etSuburbSignUp?.error = message
                firstErrorEditText = binding?.etSuburbSignUp
            }

            city.isEmpty() -> {
                binding?.etCitySignUp?.error = message
                firstErrorEditText = binding?.etCitySignUp
            }

            postCode.isEmpty() -> {
                binding?.etPostCodeSignUp?.error = message
                firstErrorEditText = binding?.etPostCodeSignUp
            }

            phone.isEmpty() -> {
                binding?.etPhoneSignUp?.error = message
                firstErrorEditText = binding?.etPhoneSignUp
            }

            contactPerson.isEmpty() -> {
                binding?.etContactSignUp?.error = message
                firstErrorEditText = binding?.etContactSignUp
            }

            mobileNumber.isEmpty() -> {
                binding?.etMobileNumberSignUp?.error = message
                firstErrorEditText = binding?.etMobileNumberSignUp
            }

            bankAccName.isEmpty() -> {
                binding?.etBankAccNameSignUp?.error = message
                firstErrorEditText = binding?.etBankAccNameSignUp
            }

            bankAccNumber.isEmpty() -> {
                binding?.etBankAccNumberSignUp?.error = message
                firstErrorEditText = binding?.etBankAccNumberSignUp
            }

            password.isEmpty() -> {
                binding?.etPasswordSignUp?.error = message
                firstErrorEditText = binding?.etPasswordSignUp
            }

            confirmPassword.isEmpty() -> {
                binding?.etConfirmPasswordSignUp?.error = message
                firstErrorEditText = binding?.etConfirmPasswordSignUp
            }
        }
        firstErrorEditText?.requestFocus()
    }

    private fun registerUser() {
        binding?.svMainForm?.isInvisible
        binding?.progressBar?.isVisible
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                binding?.progressBar?.isGone
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email
                    Toast.makeText(
                        this,
                        "You have successfully registered email $registeredEmail",
                        Toast.LENGTH_LONG
                    ).show()
                    FirebaseAuth.getInstance().signOut()
                    finish()
                } else {
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_LONG)
                        .show()
                }
            }

    }

    private fun handleBackPressed() {
        finish()
    }
}