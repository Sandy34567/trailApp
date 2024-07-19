package com.example.trailapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.trailapp.databinding.ActivityBottomBarBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar

class BottomBarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomBarBinding


    private lateinit var homeFragment: Fragment
    private lateinit var profileFragment: Fragment
    private lateinit var favoritesFragment: Fragment
    private lateinit var settingsFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBottomBarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Initialize fragments
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        favoritesFragment = FavoritesFragment()
        settingsFragment = SettingsFragment()

        // Set the initial fragment on activity start
        setCurrentFragment(homeFragment)

        // Set click listeners for BottomAppBar items using view binding

       binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.profile -> {
                    setCurrentFragment(profileFragment)
                    true
                }
                R.id.favorites -> {
                    setCurrentFragment(favoritesFragment)
                    true
                }
                R.id.setting -> {
                    setCurrentFragment(settingsFragment)
                    true
                }
                // Add more cases for other items if needed

                else -> false
            }
        }

    }
    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, fragment)
            commit()
        }
    }
}