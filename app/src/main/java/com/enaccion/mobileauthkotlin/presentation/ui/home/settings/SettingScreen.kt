package com.enaccion.mobileauthkotlin.presentation.ui.home.settings

import android.R
import android.text.style.UnderlineSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enaccion.mobileauthkotlin.presentation.ui.home.settings.components.ButtonSettings
import com.enaccion.mobileauthkotlin.ui.theme.ShapeButton

@Preview
@Composable
fun SettingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD),
                        Color(0xFFF8F9FA),
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la pantalla
        Text(
            text = "Configuración",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF242222),
            modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
        )

        Text(
            text = "Personaliza tu experiencia",
            fontSize = 16.sp,
            color = Color.Gray.copy(alpha = 0.8f),
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Contenedor de botones con fondo glassmorphism
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.15f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                ButtonSettings(
                    title = "Perfil",
                    subtitle = "Edita tu información personal",
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    iconBackgroundColor = Color(0xFF6366F1)
                )

                Spacer(modifier = Modifier.height(12.dp))

                ButtonSettings(
                    title = "Notificaciones",
                    subtitle = "Configura tus alertas",
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            Icons.Filled.Notifications,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    iconBackgroundColor = Color(0xFF10B981)
                )

                Spacer(modifier = Modifier.height(12.dp))

                ButtonSettings(
                    title = "Privacidad",
                    subtitle = "Controla tu información",
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            Icons.Filled.Lock,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    iconBackgroundColor = Color(0xFFEF4444)
                )

                Spacer(modifier = Modifier.height(12.dp))

                ButtonSettings(
                    title = "Ayuda",
                    subtitle = "Soporte y preguntas frecuentes",
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    iconBackgroundColor = Color(0xFFF59E0B)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón de cerrar sesión
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .clickable { /* Cerrar sesión */ },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.2f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.ExitToApp,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Cerrar Sesión",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}
