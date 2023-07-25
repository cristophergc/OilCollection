package com.example.oilcollection.firebase

import android.util.Log
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

    private fun getCurrentUserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }

}