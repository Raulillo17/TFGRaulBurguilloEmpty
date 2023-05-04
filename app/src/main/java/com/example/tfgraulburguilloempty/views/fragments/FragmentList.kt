package com.example.tfgraulburguilloempty.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.example.tfgraulburguilloempty.views.adapters.adapterTeams
import com.example.tfgraulburguilloempty.views.model.Team
import com.example.tfgraulburguilloempty.views.viewmodel.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentList : Fragment() {
    private lateinit var rvTeams2: RecyclerView
    private lateinit var teams: List<Team>
    private val binding get() = _binding!!
    private var _binding: FragmentList? = null
    private val viewModel: MainViewModel = MainViewModel()
    private lateinit var adapter: adapterTeams



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEquipos()
        initRV()
    }

    private fun getEquipos() {
        viewModel.getTeams().observe(viewLifecycleOwner, Observer { it ->
            it?.let{
                adapter.setEquipos(it)
                teams = it

            }
        })
    }
    fun initRV() {
        rvTeams2  = requireView().findViewById<RecyclerView>(R.id.rvTeams2)
        adapter = adapterTeams(requireContext(),R.layout.rowteams)
        rvTeams2.adapter = adapter
        rvTeams2.layoutManager = LinearLayoutManager(requireContext())

    }

}