package com.example.oilcollection.firebase

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import com.example.oilcollection.features.MyProfileActivity
import com.example.oilcollection.features.SignInActivity
import com.example.oilcollection.features.SignUpActivity
import com.example.oilcollection.features.model.UserDetails
import com.example.oilcollection.features.model.toMap
import com.example.oilcollection.models.User
import com.example.oilcollection.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class Database {

    private val mDatabase: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity, userInfo: User) {
        mDatabase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisteredSuccess()
            }
            .addOnFailureListener {
                Log.e(activity.javaClass.simpleName, "Error registering the user")
            }
    }

    fun loadUserData(activity: Activity) {
        mDatabase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)
                when (activity) {
                    is SignInActivity -> {
                        if (loggedInUser != null) {
                            activity.signInSuccessful(loggedInUser)
                        }
                    }
                    is MyProfileActivity -> {
                        if (loggedInUser != null) {
                            activity.setupUi(loggedInUser)
                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.e(activity.javaClass.simpleName, "Error registering the user")
            }
    }

    fun getCurrentUserId(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

    fun updateUserData(userId: String, userDetails: UserDetails, callback: (Boolean) -> Unit) {
        val userRef = mDatabase.collection(Constants.USERS).document(userId)
        userRef.set(userDetails.toMap(), SetOptions.merge())
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error updating user details: ${e.message}")
                callback(false)
            }
    }

}