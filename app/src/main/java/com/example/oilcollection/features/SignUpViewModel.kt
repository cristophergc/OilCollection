package com.example.oilcollection.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oilcollection.features.model.UserDetails
import com.example.oilcollection.features.model.toMap
import com.example.oilcollection.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.lang.Exception

sealed class SignUpScreenState {
    data class OnRegisterUserSubmitSuccessful(val user: User) : SignUpScreenState()
    data class OnRegisterUserSubmitError(val task: Task<AuthResult>) : SignUpScreenState()
    data class OnUpdateUserDetailsSuccessful(val user: User) : SignUpScreenState()
    data class OnUpdateUserDetailsError(val exception: Exception) : SignUpScreenState()
}

class SignUpViewModel() : ViewModel() {

    private val _screenState = MutableLiveData<SignUpScreenState>()
    val screenState: LiveData<SignUpScreenState> = _screenState

    fun registerUser(userDetails: UserDetails) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(userDetails.email, userDetails.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    val user = User(
                        firebaseUser.uid,
                        userDetails.name,
                        registeredEmail,
                        userDetails.address,
                        userDetails.suburb,
                        userDetails.city,
                        userDetails.postCode,
                        userDetails.phone,
                        userDetails.mobileNumber,
                        userDetails.bankAccName,
                        userDetails.bankAccNumber,
                        userDetails.contactPerson,
                        userDetails.password
                    )
                    _screenState.value = SignUpScreenState.OnRegisterUserSubmitSuccessful(user)

                } else {
                    _screenState.value = SignUpScreenState.OnRegisterUserSubmitError(task)
                }
            }
    }

    fun updateUserDetails(userId: String, updatedUserDetails: UserDetails) {
        val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)
        userRef.set(updatedUserDetails.toMap(), SetOptions.merge())
            .addOnSuccessListener {
                _screenState.value = SignUpScreenState.OnUpdateUserDetailsSuccessful(updatedUserDetails.toUser(userId))
            }
            .addOnFailureListener { e ->
                _screenState.value = SignUpScreenState.OnUpdateUserDetailsError(e)
            }
    }

    private fun UserDetails.toUser(userId: String): User {
        return User(
            id = userId, // Set the user ID appropriately if available
            name = name,
            email = email,
            address = address,
            suburb = suburb,
            city = city,
            postCode = postCode,
            phone = phone,
            mobileNumber = mobileNumber,
            bankAccName = bankAccName,
            bankAccNumber = bankAccNumber,
            contactPerson = contactPerson
        )
    }

}