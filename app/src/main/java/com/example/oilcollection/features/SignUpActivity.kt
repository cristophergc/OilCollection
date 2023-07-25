package com.example.oilcollection.features

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.oilcollection.databinding.ActivitySignUpBinding
import com.example.oilcollection.features.model.UserDetails
import com.example.oilcollection.firebase.Database
import com.example.oilcollection.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null
    private val viewModel by viewModels<SignUpViewModel>()
    private var firstErrorEditText: EditText? = null

    private lateinit var name: String
    private lateinit var email: String
    private lateinit var address: String
    private lateinit var suburb: String
    private lateinit var city: String
    private lateinit var postCode: String
    private lateinit var phone: String
    private lateinit var contactPerson: String
    private lateinit var mobileNumber: String
    private lateinit var bankAccName: String
    private lateinit var bankAccNumber: String
    private lateinit var password: String
    private lateinit var confirmPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarNewCustomerForm)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarNewCustomerForm?.setNavigationOnClickListener { handleBackPressed() }

        binding?.signUpButton?.setOnClickListener {
            checkInputFieldEmpty()
        }
    }

    private fun checkInputFieldEmpty() {
        name = binding?.etNameSignUp?.text.toString().trimEnd { it <= ' ' }
        email = binding?.etEmailSignUp?.text.toString().trimEnd { it <= ' ' }
        address = binding?.etAddressSignUp?.text.toString().trimEnd { it <= ' ' }
        suburb = binding?.etSuburbSignUp?.text.toString().trimEnd { it <= ' ' }
        city = binding?.etCitySignUp?.text.toString().trimEnd { it <= ' ' }
        postCode = binding?.etPostCodeSignUp?.text.toString().trimEnd { it <= ' ' }
        phone = binding?.etPhoneSignUp?.text.toString().trim { it <= ' ' }
        contactPerson = binding?.etContactSignUp?.text.toString().trimEnd { it <= ' ' }
        mobileNumber = binding?.etMobileNumberSignUp?.text.toString().trim { it <= ' ' }
        bankAccName = binding?.etBankAccNameSignUp?.text.toString().trimEnd { it <= ' ' }
        bankAccNumber = binding?.etBankAccNumberSignUp?.text.toString().trimEnd { it <= ' ' }
        password = binding?.etPasswordSignUp?.text.toString().trim { it <= ' ' }
        confirmPassword = binding?.etConfirmPasswordSignUp?.text.toString().trim { it <= ' ' }

        val message = "This field cannot be empty"
        val passwordMatchingMessage = "The password does not match. Please try again"
        var hasError = false

        when {
            name.isEmpty() -> {
                binding?.etNameSignUp?.error = message
                firstErrorEditText = binding?.etNameSignUp
                hasError = true
            }

            address.isEmpty() -> {
                binding?.etAddressSignUp?.error = message
                firstErrorEditText = binding?.etAddressSignUp
                hasError = true
            }

            suburb.isEmpty() -> {
                binding?.etSuburbSignUp?.error = message
                firstErrorEditText = binding?.etSuburbSignUp
                hasError = true
            }

            city.isEmpty() -> {
                binding?.etCitySignUp?.error = message
                firstErrorEditText = binding?.etCitySignUp
                hasError = true
            }

            postCode.isEmpty() -> {
                binding?.etPostCodeSignUp?.error = message
                firstErrorEditText = binding?.etPostCodeSignUp
                hasError = true
            }

            phone.isEmpty() -> {
                binding?.etPhoneSignUp?.error = message
                firstErrorEditText = binding?.etPhoneSignUp
                hasError = true
            }

            contactPerson.isEmpty() -> {
                binding?.etContactSignUp?.error = message
                firstErrorEditText = binding?.etContactSignUp
                hasError = true
            }

            mobileNumber.isEmpty() -> {
                binding?.etMobileNumberSignUp?.error = message
                firstErrorEditText = binding?.etMobileNumberSignUp
                hasError = true
            }

            bankAccName.isEmpty() -> {
                binding?.etBankAccNameSignUp?.error = message
                firstErrorEditText = binding?.etBankAccNameSignUp
                hasError = true
            }

            bankAccNumber.isEmpty() -> {
                binding?.etBankAccNumberSignUp?.error = message
                firstErrorEditText = binding?.etBankAccNumberSignUp
                hasError = true
            }

            password.isEmpty() -> {
                binding?.etPasswordSignUp?.error = message
                firstErrorEditText = binding?.etPasswordSignUp
                hasError = true
            }

            confirmPassword.isEmpty() -> {
                binding?.etConfirmPasswordSignUp?.error = message
                firstErrorEditText = binding?.etConfirmPasswordSignUp
                hasError = true
            }
        }

        if (password != confirmPassword) {
            binding?.etConfirmPasswordSignUp?.error = passwordMatchingMessage
            firstErrorEditText = binding?.etConfirmPasswordSignUp
            hasError = true
        }

        firstErrorEditText?.requestFocus()

        if (hasError) {
            return
        }

        val userDetails = UserDetails(
            name,
            email,
            address,
            suburb,
            city,
            postCode.toInt(),
            phone.toLong(),
            mobileNumber.toLong(),
            bankAccName,
            bankAccNumber.toLong(),
            contactPerson,
            password
        )
        registerUser(userDetails)
    }

    private fun setupObservers(){
        viewModel.screenState
    }

    private fun registerUser(userDetails: UserDetails) {
        binding?.svMainForm?.isInvisible
        binding?.progressBar?.isVisible
        viewModel.registerUser(userDetails)

    }

    fun userRegisteredSuccess(){
        Toast.makeText(
            this,
            "You have successfully registered",
            Toast.LENGTH_LONG
        ).show()
        binding?.progressBar?.isGone
        FirebaseAuth.getInstance().signOut()
        finish()
    }

    private fun handleBackPressed() {
        finish()
    }
}