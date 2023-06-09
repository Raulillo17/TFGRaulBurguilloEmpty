package com.example.tfgraulburguilloempty.views.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.adapters.adapterPlayers
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.viewmodel.MainViewModel


class FragmentoDOS : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var searchView: SearchView
    private lateinit var players: List<Player>
    private val viewModel: MainViewModel = MainViewModel()
    private lateinit var adapter: adapterPlayers
    private lateinit var rvPlayersAll: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fragmento_d_o_s, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarJugadores)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getJugadores()
        initRV()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView
        searchView?.setQueryHint("Search...")
        searchView?.setOnQueryTextListener(this)

    }

    private fun getJugadores() {

        viewModel.getJugadores().observe(viewLifecycleOwner, Observer { it ->
            it?.let{
                players = it
                adapter.setJugadores(it)


            }
        })
    }
    fun initRV() {

        rvPlayersAll  = requireView().findViewById<RecyclerView>(R.id.rvPlayersAll)
        adapter = adapterPlayers(requireContext(), R.layout.rowplayersall)
        rvPlayersAll.adapter = adapter
        rvPlayersAll.layoutManager = LinearLayoutManager(requireContext())

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        val original = ArrayList<Player>(players)
        adapter.setJugadores(original.filter { jugador ->
            jugador.firstName?.contains(query.toString(), true) ?: false
        })
        return false
    }


}