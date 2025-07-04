package com.enaccion.mobileauthkotlin.presentation.ui.auth.initial

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enaccion.mobileauthkotlin.R
import com.enaccion.mobileauthkotlin.presentation.ui.auth.initial.components.CustomButton
import com.enaccion.mobileauthkotlin.ui.theme.BackgroundButton
import com.enaccion.mobileauthkotlin.ui.theme.Black
import com.enaccion.mobileauthkotlin.ui.theme.Blue
import com.enaccion.mobileauthkotlin.ui.theme.ShapeButton

@Composable
fun InitialScreen(navigateToLogin: () -> Unit = {}, navigateToSignUp: () -> Unit = {}) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFE3F2FD),
                    Color(0xFFF8F9FA)
                ),
                startY = 0f,
                endY = Float.POSITIVE_INFINITY
            )
            ).statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        /**
         * Card para el logo
         */
        Card(
            modifier = Modifier
                .size(120.dp)
                .shadow(8.dp, CircleShape),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.enaccion),
                contentDescription = "",
                modifier = Modifier.padding(24.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        /**
         * Titulo de la pantalla initial
         */
        Text("Consultora Enaccion",
            color = Color(0xFF1A237E),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 0.5.sp
        )
        /**
         * Subtitulo
         */
        Text("Tu consultora de confianza",
            color = Color(0xFF666666),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        /**
         * Boton de registro
         */
        Button(
            onClick = { navigateToSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(28.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            )
        ){
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Registrate",
                color = Color.White,
                fontWeight = FontWeight.Bold )
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFE0E0E0),
                thickness = 1.dp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        /**
         * Iniciar sesión
         */
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {navigateToLogin()}
                .padding(16.dp)
        ){
            Text(
                text = "¿Ya tienes una cuenta?",
                color = Color(0xFF666666),
                fontSize = 14.sp
            )
            Text(
                text = "Iniciar sesión...",
                color = Blue,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(26.dp).clickable { navigateToLogin() }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

