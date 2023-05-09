package com.example.tfgraulburguilloempty.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
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
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import com.example.tfgraulburguilloempty.views.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class PlayersActivity : AppCompatActivity(), SearchView.OnQueryTextListener  {

    private lateinit var players: List<Player>
    private lateinit var rvplayers: RecyclerView
    private lateinit var adapter: adapterPlayerPerTeam
    private lateinit var viewModel: MainViewModel
    private lateinit var jugador: Player
    private lateinit var equipo: Team
    private lateinit var searchView: SearchView
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPlayersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        equipo = intent.getSerializableExtra("equipo") as Team
        title = equipo.name
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initRV()
        getPlayersByTeam(equipo.name)

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
        adapter = adapterPlayerPerTeam(this,R.layout.rowplayers)
        rvplayers.adapter = adapter
        rvplayers.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.example.tfgraulburguilloempty.R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.action_search)
 //       searchView = searchItem?.actionView as SearchView
 //       searchView.setQueryHint("Search...")
 //       searchView.setOnQueryTextListener(this)
        return true
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }

    fun onClickPlayer(v: View){
        val jugador = v.tag as Player
        val intent = Intent(this, PlayersDetailActivity::class.java)
        intent.putExtra("jugador", jugador)
        intent.putExtra("equipo", equipo)
        startActivity(intent)

    }


}