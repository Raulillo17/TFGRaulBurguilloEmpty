package com.example.tfgraulburguilloempty.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.adapters.adapterPlayers
import com.example.tfgraulburguilloempty.views.adapters.adapterTeams
import com.example.tfgraulburguilloempty.views.model.Player
import com.example.tfgraulburguilloempty.views.model.Team
import com.example.tfgraulburguilloempty.views.viewmodel.MainViewModel


class FragmentoDOS : Fragment() {
    private lateinit var jugadores: List<Player>
    private val viewModel: MainViewModel = MainViewModel()
    private lateinit var adapter: adapterPlayers

    private lateinit var rvPlayers2: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_d_o_s, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getJugadores()
        initRV()
    }

    private fun getJugadores() {
        viewModel.getJugadores().observe(viewLifecycleOwner, Observer { it ->
            it?.let{
                adapter.setJugadores(it)
                jugadores = it

            }
        })
    }
    fun initRV() {
        rvPlayers2  = requireView().findViewById<RecyclerView>(R.id.rvPlayers2)
        adapter = adapterPlayers(requireContext(),R.layout.rowteams)
        rvPlayers2.adapter = adapter
        rvPlayers2.layoutManager = LinearLayoutManager(requireContext())

    }







}