package com.enaccion.mobileauthkotlin.presentation.ui.home.savings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.enaccion.mobileauthkotlin.data.models.SavingsAccount
import com.enaccion.mobileauthkotlin.presentation.ui.home.HomeViewModel
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CustomCard
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CustomModal

@Composable
fun SavingsScreen(
        navController: NavHostController,
        viewModel: HomeViewModel = viewModel(),
        contentPadding: PaddingValues = PaddingValues()
) {
    var showModal by remember { mutableStateOf(false) }
    val savings: State<List<SavingsAccount>> = viewModel.savings.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
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
                onClick = { navController.navigate("profile") },
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
                    navController.navigate("initial") {
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


        Spacer(Modifier.height(16.dp))

        // Lista con scroll
        LazyColumn (
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(savings.value) { saving ->
                CustomCard(nombre = saving.name, saldo = saving.number, onDelete = { viewModel.deleteSaving(saving.id)})
            }
        }


        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                showModal = true
            },
            modifier = Modifier
                .width(300.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6666CC),
                contentColor = Color.White
            ))
        {
            Text(text = "Agregar cuenta de ahorro")
        }

    }
    CustomModal(
        show = showModal,
        onDismiss = { showModal = false },
        onSuccess = { accountName ->
            viewModel.createSaving(accountName)
        }
    )
}