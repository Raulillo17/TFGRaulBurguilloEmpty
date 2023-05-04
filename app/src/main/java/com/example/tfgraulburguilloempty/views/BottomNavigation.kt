package com.example.tfgraulburguilloempty.views

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.databinding.ActivityPlayersBinding
import com.example.tfgraulburguilloempty.databinding.ActivityTeamsBinding
import com.example.tfgraulburguilloempty.views.fragments.FragmentList
import com.example.tfgraulburguilloempty.views.fragments.FragmentoDOS
import com.example.tfgraulburguilloempty.views.fragments.FragmentoTRES

class BottomNavigation : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


        private lateinit var nav_view: BottomNavigationView
    private lateinit var binding: BottomNavigationView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_bottom_navigation)

            nav_view  = findViewById(R.id.nav_view)
            nav_view.setOnNavigationItemSelectedListener(this)
            setInitialFragment()

        }
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_uno -> {
                    fragment = FragmentList()
                }
                R.id.navigation_dos-> {
                    fragment = FragmentoDOS()
                }
                R.id.navigation_tres -> {
                    fragment = FragmentoTRES()
                }
            }
            replaceFragment(fragment!!)
            return true
        }

    private fun setInitialFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame, FragmentList())
        fragmentTransaction.commit()
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, fragment)
        fragmentTransaction.commit()
    }


}