package com.example.tfgraulburguilloempty.views

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.databinding.ActivityPlayersDetailBinding
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class PlayersDetailActivity : AppCompatActivity() {

    private lateinit var ivPlayerDetail: ImageView
    private lateinit var equipo: Team
    private lateinit var jugador: Player
    private lateinit var tvJersey: TextView
    private lateinit var tvPuntosDetail: TextView
    private lateinit var tvAsistenciasDetail: TextView
    private lateinit var tvRebotesDetail: TextView
    private lateinit var tvCumpleaños: TextView
    private lateinit var tvPosicionDetail: TextView
    private lateinit var tvPeso: TextView
    private lateinit var tvAltura: TextView
    private lateinit var tvPorP3: TextView
    private lateinit var tvPorT2: TextView
    private lateinit var tvPorT1: TextView

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPlayersDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayersDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        tvJersey = findViewById<TextView>(R.id.tvJersey)
        tvPuntosDetail = findViewById<TextView>(R.id.tvPuntosDetail)
        tvAsistenciasDetail = findViewById<TextView>(R.id.tvAsistenciasDetail)
        tvRebotesDetail = findViewById<TextView>(R.id.tvRebotesDetail)
        tvCumpleaños = findViewById<TextView>(R.id.tvCumpleaños)
        tvPosicionDetail = findViewById<TextView>(R.id.tvPosicionDetail)
        tvPeso = findViewById<TextView>(R.id.tvPeso)
        tvAltura = findViewById<TextView>(R.id.tvAltura)
        tvPorP3 = findViewById<TextView>(R.id.tvPorP3)
        tvPorT2 = findViewById<TextView>(R.id.tvPorT2)
        tvPorT1 = findViewById<TextView>(R.id.tvPorT1)
        ivPlayerDetail = findViewById<ImageView>(R.id.ivPlayerDetail)

        jugador = intent.getSerializableExtra("jugador") as Player
        equipo = intent.getSerializableExtra("equipo") as Team

        title = "${jugador.firstName + "  " + equipo.name}"

        showDetail()

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            msg("Has añadido a ${jugador.firstName} a favoritos" )
        }
    }

    private fun showDetail() {
        tvJersey.text = jugador.jerseyNumber
        tvPuntosDetail.text = jugador.careerPoints.toString()
        tvAsistenciasDetail.text  =jugador.carrerAssists.toString()
        tvRebotesDetail.text = jugador.careerRebounds.toString()
        tvCumpleaños.text = jugador.dateOfBirth
        tvPosicionDetail.text = jugador.position.toString()
        tvPeso.text = jugador.weight
        tvAltura.text = jugador.height.toString()
        Picasso.get().load("${jugador.headShotURL}")

    }


    private fun msg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}