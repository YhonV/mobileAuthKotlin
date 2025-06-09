package com.enaccion.mobileauthkotlin.presentation.ui.auth.signup

import androidx.lifecycle.ViewModel
import com.enaccion.mobileauthkotlin.data.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpViewModel: ViewModel(){
    private var auth : FirebaseAuth = Firebase.auth
    private var db: FirebaseFirestore = Firebase.firestore

    fun signUp(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(task.exception?.message ?: "Error desconocido")
                }
            }
    }

    suspend fun createUser(username: String, email: String){
        val user = Users (
            username = username,
            email = email
        )
        val userId = Firebase.auth.currentUser?.uid ?: return
        db.collection("users").document(userId).set(user)
    }

}