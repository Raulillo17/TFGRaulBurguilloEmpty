package com.example.tfgraulburguilloempty.views.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgraulburguilloempty.R
import com.google.firebase.auth.FirebaseAuth


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLogOut.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLogOut : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val correoElectronico = arguments?.getString("correoElectronico")
        val providerUser = arguments?.getString("providerUser")
        val emailTextView: TextView? = view?.findViewById(R.id.tvEmailDetail)
        val providerTextView: TextView? = view?.findViewById(R.id.tvProviderDeatil)
        if (emailTextView != null && providerTextView != null) {
            emailTextView.text = correoElectronico
            providerTextView.text = providerUser
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


}