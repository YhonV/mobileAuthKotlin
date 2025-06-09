package com.enaccion.mobileauthkotlin.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enaccion.mobileauthkotlin.data.models.SavingsAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class HomeViewModel: ViewModel(){

    private val _savings = MutableStateFlow<List<SavingsAccount>>(emptyList())
    val savings:  StateFlow<List<SavingsAccount>> = _savings
    private var db: FirebaseFirestore = Firebase.firestore

    init {
        getSavings()
    }

    private fun getSavings(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getAllSavings()
            }
            _savings.value = result
            Log.i("HomeViewModel", result.toString())
        }
    }

    suspend fun getAllSavings(): List<SavingsAccount> {
        return try {
            db.collection("savings")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(SavingsAccount::class.java)
                }
        } catch (e: Exception){
            emptyList()
        }
    }

    suspend fun createSaving(){
        val saldo = (100..10000).random() + (0..99).random() / 100.0
        val saving = SavingsAccount(name = "Banco Santander", number = saldo)
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

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }


}