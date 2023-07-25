package com.example.oilcollection.features

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oilcollection.features.model.UserDetails
import com.example.oilcollection.firebase.Database
import com.example.oilcollection.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

sealed class SignUpScreenState {

}

class SignUpViewModel(): ViewModel() {

    private val _screenState = MutableLiveData<SignUpScreenState>()
    val screenState: LiveData<SignUpScreenState> = _screenState

    fun test(){}

    fun registerUser(userDetails: UserDetails) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(userDetails.email, userDetails.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    val user = User(firebaseUser.uid,
                        userDetails.name,
                        registeredEmail,
                        userDetails.address,
                        userDetails.suburb,
                        userDetails.city,
                        userDetails.postCode.toInt(),
                        userDetails.phone.toLong(),
                        userDetails.mobileNumber.toLong(),
                        userDetails.bankAccName,
                        userDetails.bankAccNumber.toLong(),
                        userDetails.contactPerson,
                        userDetails.password
                    )
                    Database().registerUser(this, user)
                } else {
                    _screenState.value = Error
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

}