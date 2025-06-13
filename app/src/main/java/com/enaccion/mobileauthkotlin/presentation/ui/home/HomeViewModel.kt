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
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

        return if (currentUserId != null) {
            try {
                db.collection("users")
                    .document(currentUserId)
                    .collection("savings")
                    .get()
                    .await()
                    .documents
                    .mapNotNull { snapshot ->
                        snapshot.toObject(SavingsAccount::class.java)?.copy(id = snapshot.id)
                    }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error obteniendo savings: ${e.message}")
                emptyList()
            }
        } else {
            Log.e("HomeViewModel", "Usuario no autenticado")
            emptyList()
        }
    }

    fun createSaving(accountName: String){
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

        if (currentUserId != null) {
            val saldo = (100..10000).random() + (0..99).random() / 100.0
            val saving = SavingsAccount(name = accountName, number = saldo)

            db.collection("users")
                .document(currentUserId)
                .collection("savings")
                .add(saving)
                .addOnSuccessListener {
                    getSavings()
                    Log.i("HomeScreen", "Exito")
                }
                .addOnFailureListener {
                    Log.i("HomeScreen", "Error")
                }
        } else {
            Log.e("HomeScreen", "Usuario no autenticado")
        }
    }

    fun deleteSaving(accountId: String){
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

        if (currentUserId != null){
            db.collection("users")
                .document(currentUserId)
                .collection("savings")
                .document(accountId)
                .delete()
                .addOnSuccessListener {
                    getSavings()
                }
                .addOnFailureListener {
                    Log.e("HomeViewModel", "Error eliminando cuenta")
                }
        }
    }

    fun signOut() {
        FirebaseAuth.getInstance().signOut()
    }


}