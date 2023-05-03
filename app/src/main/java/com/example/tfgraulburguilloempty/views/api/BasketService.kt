package com.example.tfgraulburguilloempty.views.api


import com.example.tfgraulburguilloempty.views.model.Respuesta
import kotlinx.coroutines.Deferred
import retrofit2.Response

import retrofit2.http.GET

interface BasketService {
    @GET("teams")
    fun getTeams(): Deferred<Response<Respuesta>>

}