package com.example.tfgraulburguilloempty.views.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfgraulburguilloempty.views.api.MainRepository
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private var repository: MainRepository = MainRepository()

    fun getEquipos(): MutableLiveData<List<Team>> {
        val teams = MutableLiveData<List<Team>>()

        GlobalScope.launch(Dispatchers.Main) {
            teams.value = repository.getEquipos()
        }
        return teams
    }

    fun getJugadores(): MutableLiveData<List<Player>> {
        val players = MutableLiveData<List<Player>>()

        GlobalScope.launch(Dispatchers.Main) {
            players.value = repository.getJugadores()
        }
        return players
    }

    fun getPlayersByTeam(name:String): MutableLiveData<List<Player>> {
        val playersperteam = MutableLiveData<List<Player>>()

        GlobalScope.launch(Dispatchers.Main) {
            playersperteam.value = repository.getPlayersByTeam(name)
        }
        return playersperteam
    }

    }