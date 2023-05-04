package com.example.tfgraulburguilloempty.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import com.example.tfgraulburguilloempty.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity(){
    private lateinit var googlebutton: Button
    private lateinit var PasswordeditText: EditText
    private lateinit var EmaileditText: EditText
    private lateinit var loginbutton: Button
    private lateinit var ResgisterButton: Button
    private lateinit var analytics: FirebaseAnalytics
    private val GOOGLE_SIGN_IN = 100
    var db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(3000)
        //setTheme(R.style.Theme_TFGRaulBurguilloEmpty)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        ResgisterButton = findViewById<Button>(R.id.Registerbutton)
        loginbutton = findViewById<Button>(R.id.loginbutton)
        EmaileditText = findViewById<EditText>(R.id.EmaileditText)
        PasswordeditText = findViewById<EditText>(R.id.PasswordeditText)
        googlebutton = findViewById<Button>(R.id.googlebutton)
        analytics = Firebase.analytics
        title = "Autentificación"
        setUp()
        session()

    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null){
            showTeamsActivity(email, ProviderType.valueOf(provider))
        }

    }

    private fun setUp() {

        ResgisterButton.setOnClickListener {
            if (EmaileditText.text.isNotEmpty() && PasswordeditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(EmaileditText.text.toString(), PasswordeditText.text.toString()).addOnCompleteListener(){
                    if (it.isSuccessful){
                        addData()
                        showTeamsActivity(it.result?.user?.email ?: "", ProviderType.BASIC)

                    }
                    else {
                        showAlert()
                    }
                }
            }

        }


        loginbutton.setOnClickListener {
            if (EmaileditText.text.isNotEmpty() && PasswordeditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(EmaileditText.text.toString(), PasswordeditText.text.toString()).addOnCompleteListener(){
                    if (it.isSuccessful){
                        addData()
                        showTeamsActivity(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }
                    else {
                        showAlert()
                    }
                }
            }

        }

        googlebutton.setOnClickListener{
            //Configuracion

            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(
                R.string.default_web_client_id
            )).requestEmail().build()


            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }


    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showTeamsActivity(email: String, provider: ProviderType){
        val TeamsActivityIntent = Intent(this, BottomNavigation::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(TeamsActivityIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val cuenta = task.getResult(ApiException::class.java)
                if (cuenta != null){
                    val credential = GoogleAuthProvider.getCredential(cuenta.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if (it.isSuccessful){
                            addData()
                            showTeamsActivity(cuenta.email ?: "", ProviderType.GOOGLE)

                        }else{
                            showAlert()
                        }
                    }
                }
            }catch (e : ApiException){
                showAlert()
            }

        }
    }
    private fun addData() {
        val user: MutableMap<String, String> = HashMap() // diccionario key value
        user["email"] = EmaileditText.text.toString()
        user["password"] = PasswordeditText.text.toString()
        db.collection("users")
            .add(user)
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                Log.d(TAG,"DocumentSnapshot added with ID: " + documentReference.id)
            })
            .addOnFailureListener(OnFailureListener { e ->
                Log.w(TAG,"Error adding document", e)
            })
    }


}
