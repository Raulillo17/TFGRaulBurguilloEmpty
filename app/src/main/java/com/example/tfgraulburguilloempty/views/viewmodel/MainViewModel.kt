package com.example.tfgraulburguilloempty.views.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tfgraulburguilloempty.views.api.MainRepository
import com.example.tfgraulburguilloempty.views.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private var repository: MainRepository = MainRepository()

    fun getTeams(): MutableLiveData<List<Team>> {
        val teams = MutableLiveData<List<Team>>()


        GlobalScope.launch(Dispatchers.Main) {
            teams.value = repository.getTeams()
        }
        return teams
    }
}