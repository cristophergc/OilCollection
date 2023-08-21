package com.example.oilcollection.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.oilcollection.databinding.ActivityMyProfileBinding
import com.example.oilcollection.features.model.UserDetails
import com.example.oilcollection.firebase.Database
import com.example.oilcollection.models.User

class MyProfileActivity : AppCompatActivity() {
    private var binding: ActivityMyProfileBinding? = null
    private val viewModel by viewModels<BaseViewModel>()
    private lateinit var currentUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Database().loadUserData(this@MyProfileActivity)

        setupObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun setupUi(user: User) {
        currentUser = user
        binding?.etNameSignUp?.setText(user.name)
        binding?.etEmailSignUp?.setText(user.email)
        binding?.etAddressSignUp?.setText(user.address)
        binding?.etSuburbSignUp?.setText(user.suburb)
        binding?.etCitySignUp?.setText(user.city)
        binding?.etPostCodeSignUp?.setText(user.postCode.toString())
        binding?.etPhoneSignUp?.setText(user.phone.toString())
        binding?.etContactSignUp?.setText(user.contactPerson)
        binding?.etMobileNumberSignUp?.setText(user.mobileNumber.toString())
        binding?.etBankAccNameSignUp?.setText(user.bankAccName)
        binding?.etBankAccNumberSignUp?.setText(user.bankAccNumber.toString())

        binding?.updateButton?.setOnClickListener {
            updateUserDetails()
        }
    }

    private fun updateUserDetails() {
        val updatedName = binding?.etNameSignUp?.text.toString()
        val updatedEmail = binding?.etEmailSignUp?.text.toString()
        val updatedAddress = binding?.etAddressSignUp?.text.toString()
        val updatedSuburb = binding?.etSuburbSignUp?.text.toString()
        val updatedCity = binding?.etCitySignUp?.text.toString()
        val updatedPostCode = binding?.etPostCodeSignUp?.text.toString().toIntOrNull()
        val updatedPhone = binding?.etPhoneSignUp?.text.toString()
        val updatedContactPerson = binding?.etContactSignUp?.text.toString()
        val updatedMobileNumber = binding?.etMobileNumberSignUp?.text.toString()
        val updatedBankAccName = binding?.etBankAccNameSignUp?.text.toString()
        val updatedBankAccNumber = binding?.etBankAccNumberSignUp?.text.toString().toLongOrNull()

        val updatedUserDetails = UserDetails(
            updatedName,
            updatedEmail,
            updatedAddress,
            updatedSuburb,
            updatedCity,
            updatedPostCode ?: 0,
            updatedPhone.toLong(),
            updatedMobileNumber.toLong(),
            updatedBankAccName,
            updatedBankAccNumber ?: 0,
            updatedContactPerson
        )
        viewModel.updateUserDetails(currentUser.id, updatedUserDetails)
    }

    private fun setupObservers() {
        viewModel.screenState.observe(this) { screenState ->
            when (screenState) {
                is BaseScreenState.OnUpdateUserDetailsSuccessful -> {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        this@MyProfileActivity,
                        "User details updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is BaseScreenState.OnUpdateUserDetailsError -> {
                    Toast.makeText(
                        this@MyProfileActivity,
                        "Failed to update user details",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> Unit
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}