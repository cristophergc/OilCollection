package com.example.oilcollection.models

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    @DocumentId
    val id: String = "",
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

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()?: "",
        parcel.readLong(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: ""
    )

    companion object : Parceler<User> {

        override fun User.write(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeString(email)
            parcel.writeString(address)
            parcel.writeString(suburb)
            parcel.writeString(city)
            parcel.writeInt(postCode)
            parcel.writeLong(phone)
            parcel.writeString(contactPerson)
            parcel.writeLong(mobileNumber)
            parcel.writeString(bankAccName)
            parcel.writeLong(bankAccNumber)
            parcel.writeString(password)
            parcel.writeString(confirmPassword)
            parcel.writeString(fcmToken)
        }

        override fun create(parcel: Parcel): User {
            return User(parcel)
        }
    }

}