package com.enaccion.mobileauthkotlin.presentation.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enaccion.mobileauthkotlin.data.models.SavingsAccount
import com.enaccion.mobileauthkotlin.data.models.Users
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ProfileViewModel: ViewModel() {

    private val _username = MutableStateFlow<List<Users>>(emptyList())
    val username:  StateFlow<List<Users>> = _username
    private var db: FirebaseFirestore = Firebase.firestore

    init {
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getUserInfo()
            }
            _username.value = result
        }
    }

    suspend fun getUserInfo(): List<Users> {
        return try {
            db.collection("users")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Users::class.java)
                }
        } catch (e: Exception){
            emptyList()
        }
    }
}