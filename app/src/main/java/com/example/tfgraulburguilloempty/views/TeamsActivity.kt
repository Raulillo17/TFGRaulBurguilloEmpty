package com.example.tfgraulburguilloempty.views


import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.ui.AppBarConfiguration
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.databinding.ActivityTeamsBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


enum class ProviderType{
    BASIC,
    GOOGLE
}
class TeamsActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var searchView: SearchView
    private var auth: FirebaseAuth? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTeamsBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeamsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        title = "Equipos"

        //guardado de datos
        val prefs = getSharedPreferences(getString(com.example.tfgraulburguilloempty.R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.example.tfgraulburguilloempty.R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.action_search)
//        searchView = searchItem?.actionView as SearchView
//        searchView.setQueryHint("Search...")
//        searchView.setOnQueryTextListener(this)
        return true
    }
    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            toast("user: ${user.uid}, email: ${user.email}")
        }
    }
    private fun toast(msg:String){
        Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
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

    //Metodo para buscar equipo
   override fun onQueryTextChange(query: String): Boolean {
    //    val original = ArrayList<Hero>(heroes)
   //    adapter.setHeroes(original.filter { hero ->  hero.name.contains(query, true) })
       return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
       return false
   }
}
