package com.example.tfgraulburguilloempty.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.databinding.ActivityPlayersBinding
import com.example.tfgraulburguilloempty.views.adapters.adapterPlayerPerTeam
import com.example.tfgraulburguilloempty.views.adapters.adapterPlayers
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import com.example.tfgraulburguilloempty.views.viewmodel.MainViewModel


class PlayersActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {

    private lateinit var binding: ActivityPlayersBinding
    private lateinit var emailapasar: String
    private lateinit var players: List<Player>
    private lateinit var rvplayers: RecyclerView
    private lateinit var adapter: adapterPlayerPerTeam
    private lateinit var viewModel: MainViewModel
    private lateinit var jugador: Player
    private lateinit var equipo: Team
    private lateinit var searchView: SearchView
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarJugadoresPorEquipo)
        equipo = intent.getSerializableExtra("equipo") as Team
        emailapasar = intent.getSerializableExtra("emailapasar") as String
        title = equipo.name
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initRV()
        getPlayersByTeam(equipo.name)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_players_perteam, menu)
        val searchItem = menu.findItem(R.id.action_search_players_perteam)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.setQueryHint("Search...")
        searchView?.setOnQueryTextListener(this)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_search_players_perteam -> {
                Log.d("Raul", "Funciona")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getPlayersByTeam(teamName: String) {
        viewModel.getPlayersByTeam(teamName).observe(this, Observer { it ->
            it?.let{
                players = it
                adapter.setJugadoresPorEquipo(it)
            }
        })

    }

    private fun initRV() {
        rvplayers  = findViewById<RecyclerView>(R.id.rvplayers)
        adapter = adapterPlayerPerTeam(this,R.layout.rowplayersperteam)
        rvplayers.adapter = adapter
        rvplayers.layoutManager = LinearLayoutManager(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        val original = ArrayList<Player>(players)
        adapter.setJugadoresPorEquipo(original.filter { jugador ->  jugador.firstName!!.contains(query.toString(), true) })
        return false
    }

    fun onClickPlayer(v: View){
        val jugador = v.tag as Player
        val intent = Intent(this, PlayersDetailActivity::class.java)
        intent.putExtra("jugador", jugador)
        //intent.putExtra("equipo", equipo)
        intent.putExtra("emailapasar", emailapasar)
        startActivity(intent)

    }


}