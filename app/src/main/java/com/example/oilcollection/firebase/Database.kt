package com.example.oilcollection.firebase

import android.util.Log
import com.example.oilcollection.features.SignInActivity
import com.example.oilcollection.features.SignUpActivity
import com.example.oilcollection.models.User
import com.example.oilcollection.utils.Constants
import com.google.firebase.auth.FirebaseAuth
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

    fun signInUser(activity: SignInActivity) {
        mDatabase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)
                if (loggedInUser != null) {
                    activity.signInSuccessful(loggedInUser)
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

}