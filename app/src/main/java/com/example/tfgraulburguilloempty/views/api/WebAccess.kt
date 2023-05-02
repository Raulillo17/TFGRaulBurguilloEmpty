package net.azarquiel.avesretrofit.api

import com.example.tfgraulburguilloempty.views.api.BasketService
import com.example.tfgraulburguilloempty.views.api.HeaderInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header


/**
 * Created by pacopulido
 */

object WebAccess {

    val basketService: BasketService by lazy {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://free-nba.p.rapidapi.com/teams?page=0")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HeaderInterceptor())
                    .build()
            )
            .build()

        return@lazy retrofit.create(BasketService::class.java)
    }


}

