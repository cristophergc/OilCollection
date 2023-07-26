package com.example.oilcollection.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oilcollection.features.model.UserDetails
import com.example.oilcollection.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

sealed class SignUpScreenState {
    data class OnRegisterUserSubmitSuccessful(val user: User) : SignUpScreenState()
    data class OnRegisterUserSubmitError(val task: Task<AuthResult>) : SignUpScreenState()
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

}