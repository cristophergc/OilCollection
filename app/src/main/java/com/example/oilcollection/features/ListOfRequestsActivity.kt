package com.example.oilcollection.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oilcollection.R
import com.example.oilcollection.UserListManager
import com.example.oilcollection.firebase.Database

class ListOfRequestsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_requests)

        val database = Database()
        val recyclerView = findViewById<RecyclerView>(R.id.userRecyclerView)
        val adapter = UserListAdapter(UserListManager.getUserList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        database.loadAllUsersData(this) { userList ->
            UserListManager.setUsers(userList)
            adapter.notifyDataSetChanged()
        }
    }
}