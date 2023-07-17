package com.example.oilcollection.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
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

        binding?.signUpButton?.setOnClickListener {
            val name = binding?.nameEditText?.text.toString()
            val address = binding?.addressEditText?.text.toString()
            val suburb = binding?.suburbEditText?.text.toString()
            val city = binding?.cityEditText?.text.toString()
            val postCode = binding?.postCodeEditText?.text.toString()
            val phone = binding?.phoneEditText?.text.toString()
            val contactPerson = binding?.contactPersonEditText?.text.toString()
            val mobileNumber = binding?.mobileNumberEditText?.text.toString()
            val bankAccName = binding?.bankAccountNameEditText?.text.toString()
            val bankAccNumber = binding?.bankAccountNumberEditText?.text.toString()
            val password = binding?.addressEditText?.text.toString()
            val confirmPassword = binding?.addressEditText?.text.toString()

            val message = "This field cannot be empty"
            var firstErrorEditText: EditText? = null

            when {
                name.isEmpty() -> {
                    binding?.nameEditText?.error = message
                    firstErrorEditText = binding?.nameEditText
                }

                address.isEmpty() -> {
                    binding?.addressEditText?.error = message
                    firstErrorEditText = binding?.addressEditText
                }

                suburb.isEmpty() -> {
                    binding?.suburbEditText?.error = message
                    firstErrorEditText = binding?.suburbEditText
                }

                city.isEmpty() -> {
                    binding?.cityEditText?.error = message
                    firstErrorEditText = binding?.cityEditText
                }

                postCode.isEmpty() -> {
                    binding?.postCodeEditText?.error = message
                    firstErrorEditText = binding?.postCodeEditText
                }

                phone.isEmpty() -> {
                    binding?.phoneEditText?.error = message
                    firstErrorEditText = binding?.phoneEditText
                }

                contactPerson.isEmpty() -> {
                    binding?.contactPersonEditText?.error = message
                    firstErrorEditText = binding?.contactPersonEditText
                }

                mobileNumber.isEmpty() -> {
                    binding?.mobileNumberEditText?.error = message
                    firstErrorEditText = binding?.mobileNumberEditText
                }

                bankAccName.isEmpty() -> {
                    binding?.bankAccountNameEditText?.error = message
                    firstErrorEditText = binding?.bankAccountNameEditText
                }

                bankAccNumber.isEmpty() -> {
                    binding?.bankAccountNumberEditText?.error = message
                    firstErrorEditText = binding?.bankAccountNumberEditText
                }

                password.isEmpty() -> {
                    binding?.passwordEditText?.error = message
                    firstErrorEditText = binding?.passwordEditText
                }

                confirmPassword.isEmpty() -> {
                    binding?.confirmPasswordEditText?.error = message
                    firstErrorEditText = binding?.confirmPasswordEditText
                }
            }

            firstErrorEditText?.requestFocus()

            if (firstErrorEditText == null) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun handleBackPressed() {
        finish()
    }
}