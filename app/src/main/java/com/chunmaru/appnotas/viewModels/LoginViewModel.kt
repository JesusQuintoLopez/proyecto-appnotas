package com.chunmaru.appnotas.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.appnotas.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth = Firebase.auth
    var stateAlert by mutableStateOf(false)

    fun login(email: String, password: String, onSucces: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    onSucces()
                } else {
                    //email y/o password incorrecto
                    stateAlert = true
                }
            }
        } catch (e: Exception) {
            Log.d("error en la app", "${e.localizedMessage}")
        }
    }

    fun register(email: String, password: String,username:String, onSucces: () -> Unit) = viewModelScope.launch {
        try {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUser(username)
                    onSucces()
                } else {
                    //email y/o password incorrecto
                    stateAlert = true
                }
            }
        } catch (e: Exception) {
            Log.d("error en la app", "${e.localizedMessage}")
        }
    }

    private fun saveUser(username: String)=viewModelScope.launch(Dispatchers.IO) {
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        val user = UserModel(
            userId = id.toString(),
            email = email.toString(),
            username = username
        )

        FirebaseFirestore.getInstance().collection("Users")
            .add(user)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
    }

    fun closeAlert() {
        stateAlert = false
    }
}