package com.enaccion.mobileauthkotlin.presentation.ui.home.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enaccion.mobileauthkotlin.data.models.Users
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

class ProfileViewModel: ViewModel() {

    private val _user = MutableStateFlow<Users?>(null)
    val username:  StateFlow<Users?> = _user
    private var db: FirebaseFirestore = Firebase.firestore

    init {
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getUserInfo()
            }
            _user.value = result
        }
    }

    suspend fun getUserInfo(): Users? {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        Log.i("ProfileViewModel", "Current user ID: $currentUserId")
        return if (currentUserId != null) {
            try {
                val document = db.collection("users")
                    .document(currentUserId)
                    .get()
                    .await()

                val user = document.toObject(Users::class.java)
                user
            } catch (e: Exception) {
                Log.d("ProfileViewModel", "No user logged in")
                null
            }
        } else {
            null
        }
    }
}