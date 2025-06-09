package com.enaccion.mobileauthkotlin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.enaccion.mobileauthkotlin.presentation.ui.auth.initial.InitialScreen
import com.enaccion.mobileauthkotlin.presentation.ui.auth.login.LoginScreen
import com.enaccion.mobileauthkotlin.presentation.ui.auth.signup.SignUpScreen
import com.enaccion.mobileauthkotlin.presentation.ui.home.HomeScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth : FirebaseAuth) {
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
            SignUpScreen(navController = navHostController, auth = auth)
        }
        composable("home") {
            HomeScreen()
        }
    }
}