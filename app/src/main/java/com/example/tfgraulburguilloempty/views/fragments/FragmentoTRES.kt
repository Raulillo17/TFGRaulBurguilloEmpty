package com.example.tfgraulburguilloempty.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
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
class FragmentoTRES : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var searchView: SearchView
    private lateinit var imagen: String
    private var rpg: Double  =0.0
    private var apg: Double = 0.0
    private var ppg :Double = 0.0
    private lateinit var nombreequipo: String
    private lateinit var lastname: String
    private lateinit var jugadores: List<Jugador>
    private lateinit var JugadoresFav: CollectionReference
    private lateinit var documentRef: DocumentReference
    private lateinit var collectionRef: CollectionReference
    private lateinit var db: FirebaseFirestore
    private lateinit var emailapasar: String
    private lateinit var adapter: adapterJugadorFav


    // Obtén una referencia al documento que contiene la colección anidada
    //val documentRef = FirebaseFirestore.getInstance().collection("users").document()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fragmento_t_r_e_s, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarJugadoresFav)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)


        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Recuperamos el contenido desde la primera pantalla del email a pasar para meternos en su documento
        val datosRecuperados = arguments
        emailapasar = datosRecuperados!!.getString("emailapasar").toString()
        Log.d("Raul", "Documento del email $emailapasar" )



        db = FirebaseFirestore.getInstance() // Inicializar la instancia de Firebase Firestore
        collectionRef = db.collection("users")
        documentRef = collectionRef.document(emailapasar)
        JugadoresFav = documentRef.collection("JugadoresFav") // Inicializar la referencia a la colección de favoritos

        //JugadoresFav = db.collection("users").document(emailapasar).collection("JugadoresFav")


        getJugadoresFavFireBase()
        initRV()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView?.setQueryHint("Search...")
        searchView?.setOnQueryTextListener(this)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }



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
                val listajugadoresfav = ArrayList<Jugador>()
                for (document in querySnapshot.documents) {
                    // Acceder a los datos de cada jugador favorito
                    lastname = document.getString("lastName")!!
                    nombreequipo = document.getString("team")!!
                    ppg = document.getDouble("careerPoints")!!
                    apg = document.getDouble("carrerAssists")!!
                    rpg = document.getDouble("careerRebounds")!!
                    imagen = document.getString("headShotURL")!!

                    val jugador = Jugador(lastname, ppg, apg, rpg, imagen, nombreequipo)
                    // ... acceder a otros datos necesarios

                    // Agrega el objeto Item a la lista
                    listajugadoresfav.add(jugador)
                }


                // Notifica al adaptador que los datos han cambiado
                jugadores = listajugadoresfav
                adapter.setJugadoresFav(listajugadoresfav)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al recuperar los jugadores favoritos", exception)
            }
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        val original = ArrayList<Jugador>(jugadores)
        adapter.setJugadoresFav(original.filter { jugador ->
            jugador.lastName?.contains(query.toString(), true) ?: false
        })
        return false
    }



}

