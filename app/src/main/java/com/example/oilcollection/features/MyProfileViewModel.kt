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
import com.google.firebase.auth.UserProfileChangeRequest


sealed class MyProfileScreenState {
    data class OnUpdateUserDetailsSuccessful(val user: FirebaseUser) : MyProfileScreenState()
    data class OnUpdateUserDetailsError(val task: Task<Void>) : MyProfileScreenState()
}

class MyProfileViewModel : ViewModel(){

    private val _screenState = MutableLiveData<MyProfileScreenState>()
    val screenState: LiveData<MyProfileScreenState> = _screenState

    fun updateUserDetails(userDetails: UserDetails) {
        val user = FirebaseAuth.getInstance().currentUser!!
        val userProfileChangeRequest = UserProfileChangeRequest.Builder()
            .setDisplayName(userDetails.name)

            .build()

        user.updateProfile(userProfileChangeRequest)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // User details updated successfully
                    _screenState.value = MyProfileScreenState.OnUpdateUserDetailsSuccessful(user)
                } else {
                    // An error occurred
                    _screenState.value = MyProfileScreenState.OnUpdateUserDetailsError(task)
                }
            }
    }
}