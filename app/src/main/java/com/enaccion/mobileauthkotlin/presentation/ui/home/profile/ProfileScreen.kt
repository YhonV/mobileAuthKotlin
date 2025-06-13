package com.enaccion.mobileauthkotlin.presentation.ui.home.profile

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
import androidx.compose.material.icons.filled.Person
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.enaccion.mobileauthkotlin.R
import com.enaccion.mobileauthkotlin.data.models.Users
import com.enaccion.mobileauthkotlin.ui.theme.Blue


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel(), navController: NavHostController){

    var username by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    val user: State<Users?> = viewModel.username.collectAsState()

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
            ))
            .statusBarsPadding()
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
                .size(150.dp)
                .shadow(20.dp, CircleShape),
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ){
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "",
                modifier = Modifier.padding(20.dp).size(120.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Perfil de usuario",
            fontSize = 24.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(50.dp))

        val userList = user.value
        if (userList != null) {
            Text(
                "Nombre de usuario",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            OutlinedTextField(
                value = username,
                label = { Text(userList.username) },
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
        } else {
            Text("Cargando usuario...", color = Color.Gray)
        }


        Spacer(modifier = Modifier.height(20.dp))

        if (userList != null) {
            Text(
                "Correo electronico",
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            OutlinedTextField(
                value = email,
                label = { Text(userList.email) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
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
        }


    }
}