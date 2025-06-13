package com.enaccion.mobileauthkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.enaccion.mobileauthkotlin.ui.theme.MobileAuthKotlinTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import android.widget.Toast
import android.util.Log
import androidx.compose.material3.Surface
import com.enaccion.mobileauthkotlin.presentation.ui.auth.login.LoginViewModel

class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    private lateinit var auth : FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        loginViewModel.handleGoogleSignInResult(
            task,
            onSuccess = {
                // Navegar a home después del éxito
                navHostController.navigate("home") {
                    popUpTo("initial") { inclusive = true }
                }
                Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
            },
            onFailure = { error ->
                Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
                Log.e("GoogleSignIn", "Error: $error")
            }
        )
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = Firebase.auth
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setContent {
            navHostController = rememberNavController()
            MobileAuthKotlinTheme {
                // ¡Adiós al Scaffold aquí!
                // Directamente llamamos a nuestro navegador principal.
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavigationWrapper(
                        navHostController = navHostController,
                        auth = auth,
                        loginViewModel = loginViewModel,
                        onGoogleSignIn = { initiateGoogleSignIn() }
                    )
                }
            }
        }
    }


    private fun initiateGoogleSignIn() {
        val googleSignInClient = loginViewModel.createGoogleSignInClient(this)
        googleSignInClient.signOut() // Opcional: forzar selección de cuenta

        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }
}
