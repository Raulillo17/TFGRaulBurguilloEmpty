package com.example.tfgraulburguilloempty.views.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tfgraulburguilloempty.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLogOut.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLogOut : Fragment() {


    private val REQUEST_IMAGE_GET = 1
    private lateinit var ivUsuario: ImageView
    private lateinit var  emailTextView: TextView
    private lateinit var  providerTextView: TextView
    val db = FirebaseFirestore.getInstance()
    private var auth: FirebaseAuth? = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db.collection("users").document(auth!!.currentUser?.email.toString())
            .get().addOnSuccessListener { it ->
                if (it.exists()) {
                    if (it.contains("email")) {
                        emailTextView.setText(it.get("email").toString())
                    }
                    if (it.contains("password")) {
                        providerTextView.setText(it.get("password").toString())
                    }
                    if (it.contains("foto")) {
                        if (isAdded) {
                            Glide.with(requireContext())
                                .load(it.get("foto").toString())
                                .into(ivUsuario)
                        }
                    }
                }
            }

       /* val correoElectronico = arguments?.getString("correoElectronico")
        val providerUser = arguments?.getString("providerUser")


        if (emailTextView != null && providerTextView != null) {
            emailTextView.text = correoElectronico
            providerTextView.text = providerUser
        }*/
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_log_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailTextView = view?.findViewById(R.id.tvEmailDetail)!!
        providerTextView = view?.findViewById(R.id.tvProviderDeatil)!!
        ivUsuario = view?.findViewById(R.id.ivUsuario)!!

        ivUsuario.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(
                Intent.createChooser(intent, "Selecciona una imagen"),
                REQUEST_IMAGE_GET
            )

        }



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                val imagenSeleccionada = data.data
                val storageRef = FirebaseStorage.getInstance().reference
                val imageRef =
                    storageRef.child("images/${auth?.currentUser!!.uid}/imagen_perfil.jpg")
                val uploadTask = imageRef.putFile(uri)
                uploadTask.addOnSuccessListener {
                    //guardamos la url en firestore
                    imageRef.downloadUrl.addOnSuccessListener { uri ->
                        db.collection("users").document(auth?.currentUser!!.email!!)
                            .update("foto", uri.toString())
                        // carga la imagen usando Glide desde la URL de descarga
                        Glide.with(requireContext())
                            .load(uri.toString())
                            .placeholder(R.drawable.perfil1) // Imagen de espera mientras se carga la imagen
                            .into(ivUsuario)
                    }

                }.addOnFailureListener { exception ->
                    Log.d("Raul", "No se ha subido bien la imagen")
                }
            }
        }


    }
}




