package com.enaccion.mobileauthkotlin

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.enaccion.mobileauthkotlin.presentation.ui.auth.initial.InitialScreen
import com.enaccion.mobileauthkotlin.presentation.ui.auth.login.LoginScreen
import com.enaccion.mobileauthkotlin.presentation.ui.auth.signup.SignUpScreen
import com.enaccion.mobileauthkotlin.presentation.ui.home.HomeScreen
import com.enaccion.mobileauthkotlin.presentation.ui.profile.ProfileScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth : FirebaseAuth) {
    val contextToast = LocalContext.current.applicationContext
    // Este bloque se ejecuta una vez al entrar al composable.
    // Si el usuario ya está logeado, lo redirige al Home.
    LaunchedEffect(Unit) {
        if (auth.currentUser != null) {
            Toast.makeText(contextToast, "¡Bienvenido nuevamente!", Toast.LENGTH_SHORT).show()
            navHostController.navigate("home") {
                popUpTo("initial") { inclusive = true }
            }
        }
    }


    NavHost(navController = navHostController, startDestination = "initial"){
        composable ("initial") {
            InitialScreen (
                navigateToLogin = { navHostController.navigate("login")},
                navigateToSignUp = { navHostController.navigate("signup")}
            )
        }
        composable("login") {
            LoginScreen(
                navController = navHostController,
                auth = auth,
                navigateToHome = { navHostController.navigate("home") }
            )
        }
        composable("signup"){
            SignUpScreen(navController = navHostController)
        }
        composable("home") {
            HomeScreen(navHostController)
        }
        composable("profile") {
            ProfileScreen(navController = navHostController)
        }
    }
}