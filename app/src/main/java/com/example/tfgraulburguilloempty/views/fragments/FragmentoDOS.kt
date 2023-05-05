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
import com.example.tfgraulburguilloempty.views.adapters.adapterTeams
import com.example.tfgraulburguilloempty.views.model.Team
import com.example.tfgraulburguilloempty.views.viewmodel.MainViewModel


class FragmentoDOS : Fragment() {
    private val viewModel: MainViewModel = MainViewModel()
    private lateinit var adapter: adapterTeams
    private lateinit var equipos: List<Team>
    private lateinit var rvTeamsEast: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_d_o_s, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEquipos()
        initRV()
    }
    private fun getEquipos() {
        viewModel.getEquipos().observe(viewLifecycleOwner, Observer { it ->
            it?.let{
                adapter.setEquipos(it)
                equipos = it

            }
        })
    }

    fun initRV() {
        rvTeamsEast  = requireView().findViewById<RecyclerView>(R.id.rvTeamsEast)
        adapter = adapterTeams(requireContext(),R.layout.rowteams)
        rvTeamsEast.adapter = adapter
        rvTeamsEast.layoutManager = LinearLayoutManager(requireContext())

    }






}