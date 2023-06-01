package com.example.tfgraulburguilloempty.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import androidx.navigation.ui.AppBarConfiguration
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.databinding.ActivityPlayersBinding
import com.example.tfgraulburguilloempty.databinding.ActivityPlayersDetailBinding
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class PlayersDetailActivity : AppCompatActivity() {

    private var jugadorEncontrado = false
    private lateinit var emailapasar: String
    private lateinit var nombredelEquipo: String
    private lateinit var colorFondo: View
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

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayersDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarJugadoresDetail)


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
        colorFondo = findViewById<View>(R.id.llDetail)

        jugador = intent.getSerializableExtra("jugador") as Player
        equipo = intent.getSerializableExtra("equipo") as Team
        emailapasar = intent.getSerializableExtra("emailapasar") as String
        jugadorEncontrado = false

        title = "${jugador.firstName + "  " + jugador.team}"

        val db = FirebaseFirestore.getInstance() // Inicializar la instancia de Firebase Firestore
        val collectionRef = db.collection("users")
        val documentRef = collectionRef.document(emailapasar)
        val JugadoresFav =
            documentRef.collection("JugadoresFav") // Inicializar la referencia a la colección de favoritos
        val fabjugador = findViewById<FloatingActionButton>(R.id.fab)
        val drawable = fabjugador.drawable
        // Obtén el color de fondo actual del botón FAB
        val buttonColor = fabjugador.backgroundTintList?.defaultColor

        //Comprobamos si el jugador esta en favorito o no para cambiar el color del boton
        JugadoresFav.get()
            .addOnSuccessListener { snapshot ->

                for (document in snapshot.documents) {
                    val lastName = document.getString("lastName")

                    // Compara el valor de "lastName" con el jugador buscado
                    if (lastName == jugador.lastName.toString()) {
                        // El jugador existe en la colección
                        Log.d(TAG, "El jugador existe y cambiamos el color")
                        fabjugador.setImageResource(R.drawable.estrella)
                        fabjugador.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)
                        jugadorEncontrado = true
                        break
                    }
                }

                if (!jugadorEncontrado) {
                    // El jugador no existe en la colección
                    Log.d(TAG, "El jugador no existe")
                    fabjugador.setImageResource(R.drawable.favoritoapagado)
                    fabjugador.backgroundTintList = ColorStateList.valueOf(Color.BLUE)
                    jugadorEncontrado = false
                }
            }
            .addOnFailureListener { exception ->
                // Manejar el error al obtener los datos desde Firebase
                Log.e(TAG, "Error al obtener los datos: ${exception.message}")

            }


        fabjugador.setOnClickListener {
            if (!jugadorEncontrado){
                JugadoresFav.document(jugador.lastName.toString()).set(jugador)
                    .addOnSuccessListener { documentReference ->
                        fabjugador.setImageResource(R.drawable.estrella)
                        fabjugador.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)
                        jugadorEncontrado = true
                        Log.d(TAG, "Jugador favorito añadido")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error al añadir el jugador favorito", e)
                    }
                msg("Has añadido a ${jugador.firstName + " " + jugador.lastName} a favoritos")
            } else if (jugadorEncontrado){
                JugadoresFav.document(jugador.lastName.toString()).delete()
                    .addOnSuccessListener {
                        fabjugador.setImageResource(R.drawable.favoritoapagado)
                        fabjugador.backgroundTintList = ColorStateList.valueOf(Color.BLUE)
                        jugadorEncontrado = false
                        Log.d(TAG, "Jugador eliminado")
                    }
                    .addOnFailureListener {
                        Log.w(TAG, "Error al eliminar el jugador favorito")
                    }
                msg("Has eliminado a ${jugador.firstName + " " + jugador.lastName} de favoritos")
            }
        }
        showDetail()

    }


    private fun showDetail() {
        tvJersey.text = jugador.jerseyNumber
        tvPuntosDetail.text = jugador.careerPoints.toString()
        tvAsistenciasDetail.text = jugador.carrerAssists.toString()
        tvRebotesDetail.text = jugador.careerRebounds.toString()
        tvCumpleaños.text = jugador.dateOfBirth
        tvPosicionDetail.text = jugador.position.toString()
        tvPeso.text = jugador.weight
        tvAltura.text = jugador.height.toString()
        tvPorP3.text = jugador.careerPercentageThree.toString()
        tvPorT2.text = jugador.careerPercentageFieldGoal.toString()
        tvPorT1.text = jugador.careerPercentageFreethrow.toString()
        Picasso.get().load("${jugador.headShotURL}").into(ivPlayerDetail)


        //jugador.team?.let { colorFondo(it) }

    }


    private fun msg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}


