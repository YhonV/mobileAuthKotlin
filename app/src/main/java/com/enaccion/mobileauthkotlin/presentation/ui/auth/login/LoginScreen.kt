package com.enaccion.mobileauthkotlin.presentation.ui.auth.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enaccion.mobileauthkotlin.ui.theme.Black
import com.enaccion.mobileauthkotlin.ui.theme.Blue
import com.google.firebase.auth.FirebaseAuth
import com.enaccion.mobileauthkotlin.R
import com.enaccion.mobileauthkotlin.presentation.ui.auth.signup.SignUpViewModel

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    navController: NavController,
    loginViewModel: LoginViewModel,
    onGoogleSignIn: () -> Unit
)  {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    val viewModel = remember { LoginViewModel() }
    val contextToast = LocalContext.current.applicationContext

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
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 10.dp)
                    .size(30.dp)
                    .clickable{ navController.popBackStack() })
            Spacer(Modifier.weight(1f))
        }

        Card (
            modifier = Modifier
                .size(120.dp)
                .shadow(8.dp, CircleShape),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ){
            Image(
                painter = painterResource(id = R.drawable.enaccion),
                contentDescription = "",
                modifier = Modifier.padding(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        /**
         * Input de email
         */
        Text(
            "Correo electronico",
            color = Color(0xFF3e3e3e),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        OutlinedTextField(
            value = email,
            label = { Text("Correo electronico")},
            placeholder = { Text("ejemplo@dominio.com")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = Color(0xFF666666)
                )
            },
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Blue,
                unfocusedLabelColor = Color(0xFF666666),
                focusedLabelColor = Blue,
                cursorColor = Blue
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(Modifier.height(48.dp))

        /**
         * Input de password
         */
        Text(
            "Password",
            color = Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color(0xFF666666)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedBorderColor = Blue,
                unfocusedLabelColor = Color(0xFF666666),
                focusedLabelColor = Blue,
                cursorColor = Blue
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(Modifier.height(48.dp))
        Button(
            onClick = {
                viewModel.signIn(email, password,
                    onSuccess = {
                        navigateToHome()
                    },
                    onFailure = {
                        Toast.makeText(contextToast, "¡Error en el inicio de sesión!", Toast.LENGTH_SHORT).show()
                    })
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Iniciar sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Separador "O"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier.weight(1f),
                color = Color.Gray.copy(alpha = 0.5f)
            )
            Text(
                text = "O",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Gray
            )
            Divider(
                modifier = Modifier.weight(1f),
                color = Color.Gray.copy(alpha = 0.5f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de Google Sign-In
        Button(
            onClick = onGoogleSignIn,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.3f)),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Continuar con Google")
        }

    }
}
