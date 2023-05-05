package com.example.tfgraulburguilloempty.views.api


import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Respuesta
import com.example.tfgraulburguilloempty.views.model.Team
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BasketService {
    @GET("teams")
    fun getEquipos(): Deferred<Response<List<Team>>>

    @GET("players")
    fun getJugadores(): Deferred<Response<List<Player>>>


    @GET("players/team")
    fun getPlayersByTeam(@Query("name") name: String): Deferred<Response<List<Team>>>

}