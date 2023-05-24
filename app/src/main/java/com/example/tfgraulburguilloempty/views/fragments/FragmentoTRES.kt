package com.example.tfgraulburguilloempty.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.adapters.adapterJugadorFav
import com.example.tfgraulburguilloempty.views.model.Jugador
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
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
    private lateinit var JugadoresFav: CollectionReference
    private lateinit var documentRef: DocumentReference
    private lateinit var collectionRef: CollectionReference
    private lateinit var db: FirebaseFirestore
    private lateinit var emailapasar: String
    private lateinit var adapter: adapterJugadorFav


    // Obtén una referencia al documento que contiene la colección anidada
    //val documentRef = FirebaseFirestore.getInstance().collection("users").document()

    // Crea una lista para almacenar los datos de los elementos
    val itemList = ArrayList<Jugador>()

    companion object {
        fun newInstance(emailapasar: String): FragmentoTRES {
            val fragmento = FragmentoTRES()
            val args = Bundle()
            args.putString("emailapasar", emailapasar)
            fragmento.arguments = args
            return fragmento
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       emailapasar = arguments?.getString("emailapasar")!!
        db = FirebaseFirestore.getInstance() // Inicializar la instancia de Firebase Firestore
        collectionRef = db.collection("users")
        documentRef = collectionRef.document(emailapasar)
        JugadoresFav = documentRef.collection("JugadoresFav") // Inicializar la referencia a la colección de favoritos
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_t_r_e_s, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getJugadoresFavFireBase()
        initRV()
    }

    /*private fun initRV() {
        rvPlayersFav  = requireView().findViewById<RecyclerView>(R.id.rvPlayersFav)
        adapter = adapterJugadorFav(requireContext(),R.layout.rowplayersfav)
        rvPlayersFav.adapter = adapter
        rvPlayersFav.layoutManager = LinearLayoutManager(requireContext())
    }*/

    private fun initRV() {
        val rvPlayersFav = requireView().findViewById<RecyclerView>(R.id.rvPlayersFav)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        adapter = adapterJugadorFav(requireContext(), R.layout.rowplayerfavgrid)
        rvPlayersFav.adapter = adapter
        rvPlayersFav.layoutManager = gridLayoutManager
    }


    private fun getJugadoresFavFireBase() {
        JugadoresFav.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    // Acceder a los datos de cada jugador favorito
                    val nombreequipo = document.getString("team")
                    val ppg = document.getDouble("careerPoints")
                    val apg = document.getDouble("careerAssists")
                    val rpg = document.getDouble("careerRebounds")
                    val imagen = document.getString("headShotURL")

                    val jugador = Jugador(nombreequipo, ppg!!, apg!!, rpg!!, imagen)
                    // ... acceder a otros datos necesarios

                    // Agrega el objeto Item a la lista
                    itemList.add(jugador)

                    // Hacer algo con los datos recuperados
                    Log.d(TAG, "Jugador favorito: $nombreequipo")
                }
                // Notifica al adaptador que los datos han cambiado
                adapter.setJugadores(itemList)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al recuperar los jugadores favoritos", exception)
            }
    }



}

