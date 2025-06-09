package com.enaccion.mobileauthkotlin.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
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
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CustomCard
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CustomModal
import com.enaccion.mobileauthkotlin.presentation.ui.home.interfaces.SavingsAccount
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()){
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
        Spacer(Modifier.weight(1f))
        savings.value.forEach { saving ->
            CustomCard(nombre = saving.name, saldo = saving.number)
        }


        Spacer(Modifier.height(50.dp))
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
    }
    CustomModal(
        show = showModal,
        onDismiss = { showModal = false },
        onAdd = { }
    )
}


