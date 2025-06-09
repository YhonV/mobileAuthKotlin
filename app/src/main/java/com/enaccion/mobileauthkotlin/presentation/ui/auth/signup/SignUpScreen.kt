package com.enaccion.mobileauthkotlin.presentation.ui.auth.signup

import android.util.Log
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.enaccion.mobileauthkotlin.R
import com.enaccion.mobileauthkotlin.data.models.SavingsAccount
import com.enaccion.mobileauthkotlin.ui.theme.Black
import com.enaccion.mobileauthkotlin.ui.theme.Blue
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController, signUpViewModel: SignUpViewModel = SignUpViewModel()) {

    var email by remember {
        mutableStateOf("")
    }

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    val viewModel = remember { SignUpViewModel() }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
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
    ){
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier
                    .padding(vertical = 24.dp, horizontal = 10.dp)
                    .size(24.dp)
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
         * Input de nombre de usuario
         */
        Text(
            "Nombre de usuario",
            color = Color(0xFF3e3e3e),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        OutlinedTextField(
            value = username,
            label = { Text("Usuario")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color(0xFF666666)
                )
            },
            onValueChange = { username = it },
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

        Spacer(Modifier.height(10.dp))

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

        Spacer(Modifier.height(10.dp))

        /**
         * Input de password
         */
        Text(
            "Contraseña",
            color = Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
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

        Spacer(Modifier.height(10.dp))
        /**
         * Input de confirma contraseña
         */
        Text(
            "Confirma contraseña",
            color = Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
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

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.signUp(email, password,
                    onSuccess = {
                        viewModel.viewModelScope.launch {
                            viewModel.createUser(username, email)
                            navController.navigate("home")
                        }
                    },
                    onFailure = { error -> Log.i("SignUp", error) }
                )
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
            Text("Registrarse")
        }
    }
}