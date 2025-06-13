package com.enaccion.mobileauthkotlin.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.enaccion.mobileauthkotlin.ui.theme.MobileAuthKotlinTheme

// Asumo que tienes tu sealed class AppRoute como la definimos antes.
// import com.tu.paquete.navigation.AppRoute

@Composable
fun CustomBottomBar(
    navController: NavHostController,
    currentRoute: String?,
    items: List<AppRoute>
) {
    // Usamos NavigationBar en lugar de Card + TabRow. Es más limpio.
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface, // Color de fondo de la barra
    ) {
        items.forEach { screen ->
            val isSelected = currentRoute == screen.route
            NavigationBarItem(
                // El ícono a mostrar
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.label
                    )
                },
                // La etiqueta de texto
                label = { Text(screen.label) },
                // Marcamos si está seleccionado
                selected = isSelected,
                // Acción al hacer clic
                onClick = {
                    if (!isSelected) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                // Personalizamos los colores para el estado seleccionado y no seleccionado
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.surface // Color del "círculo" de fondo
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SimpleBottomBarPreview() {
    // 1. La envoltura del Tema (obligatoria para que funcionen los colores)
    MobileAuthKotlinTheme { // <-- Asegúrate que este sea el nombre de tu tema
        Surface {
            // 2. Datos de prueba para que el componente se pueda dibujar
            val previewItems = listOf(
                AppRoute.Home,
                AppRoute.Savings,
                AppRoute.Charts
            )

            // 3. Llamamos a tu componente con datos fijos
            CustomBottomBar(
                navController = rememberNavController(),
                currentRoute = AppRoute.Home.route, // Mostramos "Home" como seleccionado por defecto
                items = previewItems
            )
        }
    }
}