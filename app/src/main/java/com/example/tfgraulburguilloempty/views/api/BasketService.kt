package com.example.tfgraulburguilloempty.views.api


import com.example.tfgraulburguilloempty.views.model.Respuesta
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BasketService {
    @GET("teams")
    fun getEquipos(): Deferred<Response<Respuesta>>


    @GET("teams?division={division}")
    suspend fun getTeamsEast(@Query("division") division: String): Deferred<Response<Respuesta>>

    @GET("teams/")
    fun getTeamsWest(@Query("division") division: String): Deferred<Response<Respuesta>>
    @GET("teams/")
    fun getTeamsSoutheast(@Query("division") division: String): Deferred<Response<Respuesta>>
    @GET("teams/")
    fun getTeamsSouthwest(@Query("division") division: String): Deferred<Response<Respuesta>>

}