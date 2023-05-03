package com.example.tfgraulburguilloempty.views.api

import com.example.tfgraulburguilloempty.views.model.Team
import net.azarquiel.avesretrofit.api.WebAccess

class MainRepository {

    val service = WebAccess.basketService

    suspend fun getTeams(): List<Team> {
        val webResponse = service.getTeams().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.response
        }
        return emptyList()
    }


}