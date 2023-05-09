package com.example.tfgraulburguilloempty.views.api

import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import net.azarquiel.avesretrofit.api.WebAccess
import retrofit2.await

class MainRepository {

    val service = WebAccess.basketService

    suspend fun getEquipos(): List<Team> {
        val webResponse = service.getEquipos().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!
        }
        return emptyList()
    }



    suspend fun getJugadores(): List<Player> {
        val webResponse = service.getJugadores().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!
        }
        return emptyList()
    }

        suspend fun getPlayersByTeam(name: String): List<Player> {
            val webResponse = service.getPlayersByTeam(name).await()
            if (webResponse.isSuccessful) {
                return webResponse.body()!!
            }
            return emptyList()
        }
}







