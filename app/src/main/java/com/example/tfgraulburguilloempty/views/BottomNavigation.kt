package com.example.tfgraulburguilloempty.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.fragments.*
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC,
    GOOGLE
}
class BottomNavigation : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    private lateinit var provideapasar: String
    private lateinit var emailapasar: String
    private lateinit var nav_view: BottomNavigationView
        private lateinit var binding: BottomNavigationView



        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContentView(R.layout.activity_bottom_navigation)
            // Agrega la Toolbar como ActionBar

            nav_view  = findViewById(R.id.nav_view)
            nav_view.setOnNavigationItemSelectedListener(this)
            setInitialFragment()
            val bundle = intent.extras
            val email = bundle?.getString("email")
            val provider = bundle?.getString("provider")


            if (email != null && provider != null) {
                emailapasar = email
                provideapasar = provider
            }

            title = "Equipos"

            //guardado de datos
            val prefs = getSharedPreferences(getString(com.example.tfgraulburguilloempty.R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.putString("email", email)
            prefs.putString("provider", provider)
            prefs.apply()





        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        val inflater = menuInflater
        inflater.inflate(com.example.tfgraulburguilloempty.R.menu.menu_main, menu)

        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_logout -> {
                //borrado de datos
                val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                prefs.clear()
                prefs.apply()
                FirebaseAuth.getInstance().signOut()
                onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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
                R.id.action_logout -> {
                    /*val emailTextView: TextView? = findViewById(R.id.tvEmailDetail)
                    val providerTextView: TextView? = findViewById(R.id.tvProviderDeatil)
                    if (emailTextView != null && providerTextView != null) {
                        emailTextView.text = emailapasar
                        providerTextView.text = provideapasar
                    }*/
                    //fragment.arguments
                    fragment = FragmentLogOut()


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

    fun onClickTeam(v: View){
        val equipo = v.tag as Team
        val intent = Intent(this, PlayersActivity::class.java)
        intent.putExtra("equipo", equipo)
        startActivity(intent)

    }

/*    fun onClickPlayer(v: View){
        val jugador = v.tag as Player
        val equipo = v.tag as Team
        val intent = Intent(this, PlayersDetailActivity::class.java)
        intent.putExtra("jugador", jugador)
        intent.putExtra("equipo", equipo)
        startActivity(intent)

    }*/

    fun onClickLogOut(v: View){
        //borrado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()
        FirebaseAuth.getInstance().signOut()
        onBackPressed()

    }


}