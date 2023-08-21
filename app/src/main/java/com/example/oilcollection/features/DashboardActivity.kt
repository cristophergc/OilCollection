package com.example.oilcollection.features

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.oilcollection.R
import com.example.oilcollection.UserListManager
import com.example.oilcollection.databinding.ActivityMyProfileBinding
import com.example.oilcollection.databinding.DashboardContentBinding
import com.example.oilcollection.firebase.Database
import com.example.oilcollection.models.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var requestCollectionButton: AppCompatButton
    private lateinit var currentUser: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        Database().loadUserData(this@DashboardActivity)

        requestCollectionButton = findViewById(R.id.requestCollectionButton)
        requestCollectionButton.setOnClickListener {
            Toast.makeText(
                this@DashboardActivity,
                "Collection Requested Successfully",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this, ListOfRequestsActivity::class.java)
            startActivity(intent)
        }
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_my_profile -> {
                    val intent = Intent(this, MyProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_sign_out -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, SignInActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
                // Handle other navigation items here
            }
            // Close the drawer after handling the item
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun currentUser(user: User){
        currentUser = user
        UserListManager.addUser(currentUser)
    }
}