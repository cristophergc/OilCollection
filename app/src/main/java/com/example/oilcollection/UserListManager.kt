package com.example.oilcollection

import com.example.oilcollection.models.User

object UserListManager {
    private val userList = mutableListOf<User>()

    fun addUser(user: User) {
        userList.add(user)
    }

    fun getUserList(): List<User> {
        return userList.toList()
    }

    fun setUsers(users: List<User>) {
        userList.clear()
        userList.addAll(users)
    }
}