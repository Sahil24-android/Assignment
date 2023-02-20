package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.assignment.data.PrefManager
import com.example.assignment.ui.CartFragment
import com.example.assignment.ui.HomeFragment
import com.example.assignment.ui.MyOrderFragment
import com.example.assignment.ui.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.nav_view)

        prefManager = PrefManager(this)

        if (prefManager.isLogin()) {
            loadFragment(HomeFragment())
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                R.id.navigation_cart -> {
                    loadFragment(CartFragment())

                    true
                }
                R.id.navigation_my_order -> {
                    loadFragment(MyOrderFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            addToBackStack(null)
            commit()
        }
    }
    }
