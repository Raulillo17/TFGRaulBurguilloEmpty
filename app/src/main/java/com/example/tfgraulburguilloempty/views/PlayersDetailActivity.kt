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
import com.example.tfgraulburguilloempty.views.model.Jugador
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso


class PlayersDetailActivity : AppCompatActivity() {

    private lateinit var jugadorFav: Player
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
        //equipo = intent.getSerializableExtra("equipo") as Team
        emailapasar = intent.getSerializableExtra("emailapasar") as String
        jugadorEncontrado = false

        title = "${jugador.lastName + "  " + jugador.team}"

        val db = FirebaseFirestore.getInstance() // Inicializar la instancia de Firebase Firestore
        val collectionRef = db.collection("users")
        val documentRef = collectionRef.document(emailapasar)
        val JugadoresFav =
            documentRef.collection("JugadoresFav") // Inicializar la referencia a la colección de favoritos
        val fabjugador = findViewById<ExtendedFloatingActionButton>(R.id.fab)

        // Obtén el color de fondo actual del botón FAB
        //val buttonColor = fabjugador.backgroundTintList?.defaultColor

        //Comprobamos si la subcollecion existe para crearla o no





        //Comprobamos si el jugador esta en favorito o no para cambiar el color del boton
        JugadoresFav.get()
            .addOnSuccessListener { snapshot ->
                for (document in snapshot.documents) {
                    val lastName = document.getString("lastName")

                    // Compara el valor de "lastName" con el jugador buscado
                    if (lastName == jugador.lastName.toString()) {
                        // El jugador existe en la colección
                        Log.d(TAG, "El jugador existe y cambiamos el color")
                        fabjugador.setIconResource(R.drawable.estrella)
                        fabjugador.text = "Añadido a Favoritos"
                        //fabjugador.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)
                        jugadorEncontrado = true
                        break
                    }
                }

                if (!jugadorEncontrado) {
                    // El jugador no existe en la colección
                    Log.d(TAG, "El jugador no existe")
                    fabjugador.setIconResource(R.drawable.favoritoapagado)
                    fabjugador.text = ""
                    //fabjugador.backgroundTintList = ColorStateList.valueOf(Color.CYAN)
                    jugadorEncontrado = false
                }
            }
            .addOnFailureListener { exception ->
                // Manejar el error al obtener los datos desde Firebase
                Log.e(TAG, "Error al obtener los datos: ${exception.message}")

            }


        fabjugador.setOnClickListener {
            if (emailapasar != null) {
                val jugadoresFavRef = FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(emailapasar)
                    .collection("JugadoresFav")

                jugadoresFavRef.whereEqualTo("id", jugador.id)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (querySnapshot.isEmpty && !jugadorEncontrado) {
                            // El jugador no está en la subcolección, agregarlo
                            jugadoresFavRef.document(jugador.lastName.toString()).set(jugador)
                                .addOnSuccessListener {
                                    fabjugador.setIconResource(R.drawable.estrella)
                                    fabjugador.text = "Añadido a Favoritos"
                                    //fabjugador.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)
                                    jugadorEncontrado = true
                                    // Jugador agregado exitosamente
                                    Toast.makeText(this, "Jugador agregado a favoritos", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { e ->
                                    // Error al agregar el jugador
                                    Log.e(TAG, "Error al agregar el jugador a favoritos", e)
                                    Toast.makeText(this, "Error al agregar el jugador a favoritos", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            // El jugador ya está en la subcolección, eliminarlo
                            for (document in querySnapshot.documents) {
                                jugadoresFavRef.document(document.id)
                                    .delete()
                                    .addOnSuccessListener {
                                        fabjugador.setIconResource(R.drawable.favoritoapagado)
                                        fabjugador.text = ""
                                        //fabjugador.backgroundTintList = ColorStateList.valueOf(Color.CYAN)
                                        jugadorEncontrado = false
                                        // Jugador eliminado exitosamente
                                        Toast.makeText(this, "Jugador eliminado de favoritos", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener { e ->
                                        // Error al eliminar el jugador
                                        Log.e(TAG, "Error al eliminar el jugador de favoritos", e)
                                        Toast.makeText(this, "Error al eliminar el jugador de favoritos", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                    }
                    .addOnFailureListener { exception ->
                        // Error al consultar la subcolección
                        Log.e(TAG, "Error al consultar la subcolección JugadoresFav", exception)
                        Toast.makeText(this, "Error al consultar la subcolección JugadoresFav", Toast.LENGTH_SHORT).show()
                    }
            }
            /*if (!jugadorEncontrado){
                JugadoresFav.document(jugador.lastName.toString()).set(jugador)
                    .addOnSuccessListener { documentReference ->
                        fabjugador.setIconResource(R.drawable.estrella)
                        fabjugador.text = "Añadido a Favoritos"
                        //fabjugador.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)
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
                        fabjugador.setIconResource(R.drawable.favoritoapagado)
                        fabjugador.text = ""
                        //fabjugador.backgroundTintList = ColorStateList.valueOf(Color.CYAN)
                        jugadorEncontrado = false
                        Log.d(TAG, "Jugador eliminado")
                    }
                    .addOnFailureListener {
                        Log.w(TAG, "Error al eliminar el jugador favorito")
                    }
                msg("Has eliminado a ${jugador.firstName + " " + jugador.lastName} de favoritos")
            }*/
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
        tvPorP3.text = jugador.careerPercentageThree.toString() + " %"
        tvPorT2.text = jugador.careerPercentageFieldGoal.toString() + " %"
        tvPorT1.text = jugador.careerPercentageFreethrow.toString() + " %"
        Picasso.get().load("${jugador.headShotURL}").into(ivPlayerDetail)


        //jugador.team?.let { colorFondo(it) }

    }


    private fun msg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}


