package com.example.tfgraulburguilloempty.views

import android.annotation.SuppressLint
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

        title = "${jugador.firstName + "  " + jugador.team}"

        val db = FirebaseFirestore.getInstance() // Inicializar la instancia de Firebase Firestore
        val collectionRef = db.collection("users")
        val documentRef = collectionRef.document(emailapasar)
        val JugadoresFav = documentRef.collection("JugadoresFav") // Inicializar la referencia a la colección de favoritos
        val fabjugador = findViewById<FloatingActionButton>(R.id.fab)
        // Obtén el color de fondo actual del botón FAB
        val buttonColor = fabjugador.backgroundTintList?.defaultColor


        //Comprobamos si el jugador esta en favorito o no para cambiar el color del boton
        JugadoresFav.get()
            .addOnSuccessListener{ snapshot ->
            if (snapshot != null) {
                for (document in snapshot.documents) {
                    // Compara el objeto en cada documento con el objeto buscado
                    if (document.getString("id") == jugador.id.toString()) {
                        // El objeto está presente en la colección
                        Log.d(TAG, "El jugador existe y cambiamos el color")
                        fabjugador.setBackgroundColor(Color.RED)
                        break
                    }else{
                        Log.d(TAG, "El jugador no existe ")
                        fabjugador.setBackgroundColor(Color.BLUE)
                    }

                }
            }
        }

        showDetail()

        fabjugador.setOnClickListener {
            if (buttonColor == Color.BLUE) {
                JugadoresFav.document(jugador.lastName!!).set(jugador)
                    .addOnSuccessListener { documentReference ->
                        fabjugador.setBackgroundColor(Color.RED)
                        Log.d(TAG, "Jugador favorito añadido")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error al añadir el jugador favorito", e)
                    }
                msg("Has añadido a ${jugador.firstName + " " + jugador.lastName} a favoritos" )
            }else{
                JugadoresFav.document(jugador.lastName!!).delete()
                    .addOnSuccessListener {
                        fabjugador.setBackgroundColor(Color.BLUE)
                        Log.d(TAG, "Jugador eliminado")
                    }
                    .addOnFailureListener {
                        Log.w(TAG, "Error al eliminar el jugador favorito")
                    }
                msg("Has eliminado a ${jugador.firstName + " " + jugador.lastName} de favoritos" )
            }
            }



/*            if (jugador.equals(jugador.id))
            JugadoresFav.add(jugador)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "Jugador favorito añadido con ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error al añadir el jugador favorito", e)
                }
            msg("Has añadido a ${jugador.firstName + " " + jugador.lastName} a favoritos" )*/



        }


    @SuppressLint("ResourceAsColor")
    private fun showDetail() {
        tvJersey.text = jugador.jerseyNumber
        tvPuntosDetail.text = jugador.careerPoints.toString()
        tvAsistenciasDetail.text  =jugador.carrerAssists.toString()
        tvRebotesDetail.text = jugador.careerRebounds.toString()
        tvCumpleaños.text = jugador.dateOfBirth
        tvPosicionDetail.text = jugador.position.toString()
        tvPeso.text = jugador.weight
        tvAltura.text = jugador.height.toString()
        tvPorP3.text = jugador.careerPercentageThree.toString()
        tvPorT2.text = jugador.careerPercentageFieldGoal.toString()
        tvPorT1.text = jugador.careerPercentageFreethrow.toString()
        Picasso.get().load("${jugador.headShotURL}").into(ivPlayerDetail)
        colorFondo.background = colorFondo(jugador.team)

        //jugador.team?.let { colorFondo(it) }

    }

    @SuppressLint("ResourceAsColor")
    private fun colorFondo(name: String?): Drawable? {
        when (name) {
            "Orlando Magic" -> colorFondo.setBackgroundColor(R.color.Orlando)
            "Atlanta Hawks" -> colorFondo.setBackgroundColor(R.color.Atlanta)
            "Boston Celtics" -> colorFondo.setBackgroundColor(R.color.Boston)
            "Milwaukee Bucks" -> colorFondo.setBackgroundColor(R.color.Bucks)
            "Chicago Bulls" -> colorFondo.setBackgroundColor(R.color.Bulls)
            "Cleveland Cavaliers" -> colorFondo.setBackgroundColor(R.color.Cleveland)
            "LA Clippers" -> colorFondo.setBackgroundColor(R.color.Clippers)
            "New York Knicks" -> colorFondo.setBackgroundColor(R.color.Nicks)
            "Dallas Mavericks" -> colorFondo.setBackgroundColor(R.color.Dallas)
            "Denver Nuggets" -> colorFondo.setBackgroundColor(R.color.Denver)
            "Detroit Pistons" -> colorFondo.setBackgroundColor(R.color.Detroit)
            "Indiana Pacers" -> colorFondo.setBackgroundColor(R.color.Pacers)
            "Philadelphia 76ers" -> colorFondo.setBackgroundColor(R.color.Phipadelphia)
            "Memphis Grizzlies" -> colorFondo.setBackgroundColor(R.color.Memphis)
            "Miami Heat" -> colorFondo.setBackgroundColor(R.color.Miami)
            "Minnesota Timberwolves" -> colorFondo.setBackgroundColor(R.color.Minesota)
            "Toronto Raptors" -> colorFondo.setBackgroundColor(R.color.Raptors)
            "Houston Rockets" -> colorFondo.setBackgroundColor(R.color.Rockets)
            "Golden State Warriors" -> colorFondo.setBackgroundColor(R.color.Warriors)
            "Washington Wizards" -> colorFondo.setBackgroundColor(R.color.Wizards)
            "Charlotte Hornets" -> colorFondo.setBackgroundColor(R.color.Hornets)
            "Utah Jazz" -> colorFondo.setBackgroundColor(R.color.Jazz)
            "Brooklyn Nets" -> colorFondo.setBackgroundColor(R.color.Nets)
            "Oklahoma City Thunder" -> colorFondo.setBackgroundColor(R.color.Oklahoma)
            "New Orleans Pelicans" -> colorFondo.setBackgroundColor(R.color.Orleans)
            "Portland Trail Blazers" -> colorFondo.setBackgroundColor(R.color.Portland)
            "Los Angeles Lakers" -> colorFondo.setBackgroundColor(R.color.Lakers)
            "Sacramento Kings" -> colorFondo.setBackgroundColor(R.color.Sacramento)
            "Phoenix Suns" -> colorFondo.setBackgroundColor(R.color.Suns)
            "San Antonio Spurs" -> colorFondo.setBackgroundColor(R.color.Spurs)
        }
        return null
    }



    private fun msg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}