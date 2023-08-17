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

fun UserDetails.toMap(): Map<String, Any> {
    return mapOf(
        "name" to name,
        "email" to email,
        "address" to address,
        "suburb" to suburb,
        "city" to city,
        "postCode" to postCode,
        "phone" to phone,
        "mobileNumber" to mobileNumber,
        "bankAccName" to bankAccName,
        "bankAccNumber" to bankAccNumber,
        "contactPerson" to contactPerson,
        "password" to password,
        "confirmPassword" to confirmPassword,
        "fcmToken" to fcmToken
    )
}
