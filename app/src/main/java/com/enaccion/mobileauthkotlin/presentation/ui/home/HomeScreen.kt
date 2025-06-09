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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CustomCard
import com.enaccion.mobileauthkotlin.presentation.ui.home.components.CustomModal
import com.enaccion.mobileauthkotlin.presentation.viewmodel.Saving
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen(db: FirebaseFirestore){
    var showModal by remember { mutableStateOf(false) }

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
        CustomCard()

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

fun createSaving(db: FirebaseFirestore){
    val random = (1 .. 10000).random()
    val saving = Saving(name = "Cuenta de ahorro", number = random)
    db.collection("savings").add(saving)
        .addOnSuccessListener {
            Log.i("HomeScreen", "Exito")
        }
        .addOnFailureListener {
            Log.i("HomeScreen", "Error")
        }
        .addOnCompleteListener {
            Log.i("HomeScreen", "Completado")
        }
}


