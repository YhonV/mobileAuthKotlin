package com.enaccion.mobileauthkotlin.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.enaccion.mobileauthkotlin.data.models.SavingsAccount
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CustomCard
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CustomModal

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = HomeViewModel()){
    var showModal by remember { mutableStateOf(false) }
    val savings: State<List<SavingsAccount>> = viewModel.savings.collectAsState()

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
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { /* Ir a perfil */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF196f3d),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text(text = "Perfil")
            }

            Button(
                onClick = {
                    viewModel.signOut()
                    navController.navigate("login") {
                        popUpTo("main") { inclusive = true }
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFb12a0d),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
            ) {
                Text(text = "Cerrar sesión")
            }
        }


        Spacer(Modifier.weight(1f))

        savings.value.forEach { saving ->
            CustomCard(nombre = saving.name, saldo = saving.number)
        }


        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                showModal = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp))
        {
            Text(text = "Agregar cuenta de ahorro")
        }

        Spacer(Modifier.weight(1f))
    }
    CustomModal(
        show = showModal,
        onDismiss = { showModal = false },
        onAdd = { }
    )
}


