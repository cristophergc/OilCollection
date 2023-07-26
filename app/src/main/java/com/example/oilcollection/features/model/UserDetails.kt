package com.example.oilcollection.features.model

import com.google.firebase.firestore.DocumentId

data class UserDetails(
    val name: String = "",
    val email: String = "",
    val address: String = "",
    val suburb: String = "",
    val city: String = "",
    val postCode: Int = 0,
    val phone: Long = 0,
    val mobileNumber: Long = 0,
    val bankAccName: String = "",
    val bankAccNumber: Long = 0,
    val contactPerson: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val fcmToken: String = ""

)