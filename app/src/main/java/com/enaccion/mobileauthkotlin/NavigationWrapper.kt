package com.enaccion.mobileauthkotlin

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.enaccion.mobileauthkotlin.navigation.AppRoute
import com.enaccion.mobileauthkotlin.navigation.CustomBottomBar
import com.enaccion.mobileauthkotlin.presentation.ui.auth.initial.InitialScreen
import com.enaccion.mobileauthkotlin.presentation.ui.auth.login.LoginScreen
import com.enaccion.mobileauthkotlin.presentation.ui.auth.login.LoginViewModel
import com.enaccion.mobileauthkotlin.presentation.ui.auth.signup.SignUpScreen
import com.enaccion.mobileauthkotlin.presentation.ui.home.HomeScreen
import com.enaccion.mobileauthkotlin.presentation.ui.home.charts.ChartScreen
import androidx.compose.runtime.*
import com.enaccion.mobileauthkotlin.presentation.ui.home.profile.ProfileScreen
import com.enaccion.mobileauthkotlin.presentation.ui.home.savings.SavingsScreen
import com.enaccion.mobileauthkotlin.presentation.ui.home.settings.SettingScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    loginViewModel: LoginViewModel,
    onGoogleSignIn: () -> Unit
) {
    val contextToast = LocalContext.current.applicationContext

    // Lista de rutas que tendrán la barra inferior
    val screensWithBottomBar = listOf(
        AppRoute.Home.route,
        AppRoute.Savings.route,
        AppRoute.Charts.route
    )

    // Lista de items para pasarle a nuestra barra de UI
    val bottomBarItems = listOf(
        AppRoute.Home,
        AppRoute.Savings,
        AppRoute.Charts,
        AppRoute.Settings
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val shouldShowBottomBar = currentRoute in screensWithBottomBar

    // LaunchedEffect para auto-login (tu código original, está perfecto)
    LaunchedEffect(Unit) {
        if (auth.currentUser != null) {
            Toast.makeText(contextToast, "¡Bienvenido nuevamente!", Toast.LENGTH_SHORT).show()
            navHostController.navigate(AppRoute.Home.route) {
                popUpTo(AppRoute.Initial.route) { inclusive = true }
            }
        }
    }

    Scaffold (
        bottomBar = {
            if (shouldShowBottomBar) {
                // Llamamos a nuestro nuevo componente de UI
                CustomBottomBar(
                    navController = navHostController,
                    currentRoute = currentRoute,
                    items = bottomBarItems
                )
            }
        }
    ) { innerPadding ->
        // UN SOLO NAVHOST PARA TODA LA APP
        NavHost(
            navController = navHostController,
            startDestination = AppRoute.Initial.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // --- Rutas de Autenticación ---
            composable(AppRoute.Initial.route) {
                InitialScreen(
                    navigateToLogin = { navHostController.navigate(AppRoute.Login.route) },
                    navigateToSignUp = { navHostController.navigate(AppRoute.SignUp.route) }
                )
            }
            composable(AppRoute.Login.route) {
                LoginScreen(
                    navController = navHostController,
                    navigateToHome = {
                        navHostController.navigate(AppRoute.Home.route) {
                            popUpTo(AppRoute.Initial.route) { inclusive = true }
                        }
                    },
                    loginViewModel = loginViewModel,
                    onGoogleSignIn = onGoogleSignIn
                )
            }
            composable(AppRoute.SignUp.route) {
                SignUpScreen(navController = navHostController)
            }

            // --- Rutas Principales de la App ---
            composable(AppRoute.Home.route) {
                // Tu HomeScreen ahora se muestra aquí
                HomeScreen()
            }
            composable(AppRoute.Savings.route) {
                SavingsScreen(navHostController)
            }
            composable(AppRoute.Charts.route) {
                ChartScreen()
            }
            composable(AppRoute.Profile.route) {
                ProfileScreen(navController = navHostController)
            }
            composable(AppRoute.Settings.route) {
                SettingScreen()
            }
        }
    }
}