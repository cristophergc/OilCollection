package com.example.oilcollection.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.oilcollection.databinding.ActivityNewCustomerFormBinding

class NewCustomerFormActivity : AppCompatActivity() {

    private var binding: ActivityNewCustomerFormBinding? = null

    private var firstErrorEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCustomerFormBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarNewCustomerForm)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarNewCustomerForm?.setNavigationOnClickListener { handleBackPressed() }

        binding?.signUpButton?.setOnClickListener {

            checkInputFieldError()

            if (firstErrorEditText == null) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun checkInputFieldError(){
        val name = binding?.etNameSignUp?.text.toString().trimEnd { it <= ' ' }
        val address = binding?.etAddressSignUp?.text.toString().trimEnd { it <= ' ' }
        val suburb = binding?.etSuburbSignUp?.text.toString().trimEnd { it <= ' ' }
        val city = binding?.etCitySignUp?.text.toString().trimEnd { it <= ' ' }
        val postCode = binding?.etPostCodeSignUp?.text.toString().trimEnd { it <= ' ' }
        val phone = binding?.etPhoneSignUp?.text.toString().trim { it <= ' ' }
        val contactPerson = binding?.etContactSignUp?.text.toString().trimEnd { it <= ' ' }
        val mobileNumber = binding?.etMobileNumberSignUp?.text.toString().trim { it <= ' ' }
        val bankAccName = binding?.etBankAccNameSignUp?.text.toString().trimEnd { it <= ' ' }
        val bankAccNumber = binding?.etBankAccNumberSignUp?.text.toString().trimEnd { it <= ' ' }
        val password = binding?.etPasswordSignUp?.text.toString().trim { it <= ' ' }
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

    private fun handleBackPressed() {
        finish()
    }
}