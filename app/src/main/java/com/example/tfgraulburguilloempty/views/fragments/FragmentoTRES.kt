package com.example.tfgraulburguilloempty.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.adapters.adapterJugadorFav
import com.example.tfgraulburguilloempty.views.adapters.adapterPlayers
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoTRES.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoTRES : Fragment() {
    private lateinit var adapter: adapterJugadorFav
    private lateinit var rvJugadorFav: RecyclerView
    val db = FirebaseFirestore.getInstance() // Inicializar la instancia de Firebase Firestore

    val usuariosRef = db.collection("users") // Referencia a la colección "usuarios"
    val JugadoresFav = usuariosRef.document("usuario_id").collection("JugadoresFav") // Referencia a la colección "favoritos" dentro del documento de un usuario específico


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_t_r_e_s, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getJugadoresFavFireBase()
        initRV()
    }

    private fun initRV() {
        rvJugadorFav  = requireView().findViewById<RecyclerView>(R.id.rvJugadorFav)
        adapter = adapterJugadorFav(requireContext(),R.layout.rowplayersfav)
        rvJugadorFav.adapter = adapter
        rvJugadorFav.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getJugadoresFavFireBase() {
        JugadoresFav.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    // Acceder a los datos de cada jugador favorito
                    val nombre = document.getString("nombre")
                    val equipo = document.getString("equipo")
                    // ... acceder a otros datos necesarios

                    // Hacer algo con los datos recuperados
                    Log.d(TAG, "Jugador favorito: $nombre - $equipo")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al recuperar los jugadores favoritos", exception)
            }
    }

}

