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
import com.example.tfgraulburguilloempty.views.model.Height
import com.example.tfgraulburguilloempty.views.model.Jugador
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Position
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.type.DateTime

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
    private lateinit var weigth: String
    private lateinit var firstname: String
    private lateinit var position: String
    private lateinit var jerseyNumber: String
    private var identificador: Long = 0
    private lateinit var height: String
    private lateinit var dateOfBirth: String
    private lateinit var dateLastUpdated: String
    private var careerTurnovers: Double  =0.0
    private var careerPercentageThree: Double  =0.0
    private var careerPercentageFreethrow: Double  =0.0
    private var careerPercentageFieldGoal: Double  =0.0
    private lateinit var age: String
    private var careerBlocks: Double  =0.0
    private lateinit var searchView: SearchView
    private lateinit var imagen: String
    private var rpg: Double  =0.0
    private var apg: Double = 0.0
    private var ppg :Double = 0.0
    private lateinit var nombreequipo: String
    private lateinit var lastname: String
    private lateinit var jugadores: List<Jugador>
    private lateinit var players: List<Player>
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
                val listaplayersfav = ArrayList<Player>()
                for (document in querySnapshot.documents) {
                    // Acceder a los datos de cada jugador favorito
                    //Datos Player
                    age = document.getString("age")!!
                    careerBlocks = document.getDouble("careerBlocks")!!
                    careerPercentageFieldGoal = document.getDouble("careerPercentageFieldGoal")!!
                    careerPercentageFreethrow = document.getDouble("careerPercentageFreethrow")!!
                    careerPercentageThree = document.getDouble("careerPercentageThree")!!
                    careerTurnovers = document.getDouble("careerTurnovers")!!
                    dateLastUpdated = document.getString("dateLastUpdated")!!
                    dateOfBirth = document.getString("dateOfBirth")!!
                    height = document.getString("height")!!
                    identificador = document.getLong("id")!!
                    jerseyNumber = document.getString("jerseyNumber")!!
                    position = document.getString("position")!!
                    firstname = document.getString("firstName")!!
                    weigth = document.getString("weight")!!



                    //Datos jugador
                    lastname = document.getString("lastName")!!
                    nombreequipo = document.getString("team")!!
                    ppg = document.getDouble("careerPoints")!!
                    apg = document.getDouble("carrerAssists")!!
                    rpg = document.getDouble("careerRebounds")!!
                    imagen = document.getString("headShotURL")!!

                    //llenamos el el objeto player con todos los datos de firebase
                    val player = Player(identificador, firstname, lastname, nombreequipo,
                        position, dateOfBirth, height, weigth, jerseyNumber, age,
                        ppg, careerBlocks, apg, rpg, careerTurnovers, careerPercentageThree, careerPercentageFreethrow, careerPercentageFieldGoal, imagen, dateLastUpdated)

                   //llenamos el objeto jugador con los valores que nos interesa sacar de Firebase, teniendo en cuenta el contructor que tienen
                    //val jugador = Jugador(lastname, ppg, apg, rpg, imagen, nombreequipo)
                    // ... acceder a otros datos necesarios

                    // Agrega el objeto Item a la lista
                    //listajugadoresfav.add(jugador)
                    listaplayersfav.add(player)
                }


                // Notifica al adaptador que los datos han cambiado
                //jugadores = listajugadoresfav
                players = listaplayersfav
                adapter.setJugadoresFav(listaplayersfav)
                //adapter.setJugadoresFav(listajugadoresfav)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al recuperar los jugadores favoritos", exception)
            }
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        val original = ArrayList<Player>(players)
        adapter.setJugadoresFav(original.filter { jugador ->
            jugador.lastName?.contains(query.toString(), true) ?: false
        })
        return false
    }



}

