package com.enaccion.mobileauthkotlin.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

// Un solo lugar para todas las rutas de tu app
sealed class AppRoute(val route: String, val label: String, val icon: ImageVector) {
    // Rutas de la barra de navegación
    object Home : AppRoute("home", "Inicio", Icons.Default.Home)
    object Savings : AppRoute("savings", "Ahorros", Icons.Default.Info) // Reemplaza con tu ícono
    object Charts : AppRoute("charts", "Gráficos", Icons.Default.ShoppingCart) // Reemplaza con tu ícono

    // Rutas que no están en la barra
    object Initial : AppRoute("initial", "Inicial", Icons.Default.Person)
    object Login : AppRoute("login", "Login", Icons.Default.Person)
    object SignUp : AppRoute("signup", "Registro", Icons.Default.Person)
    object Profile : AppRoute("profile", "Perfil", Icons.Default.Person)
    object Settings : AppRoute("settings", "Ajustes", Icons.Default.Person)
}