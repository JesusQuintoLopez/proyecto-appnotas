package com.chunmaru.appnotas.views.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun BlankView(navController: NavController){
    LaunchedEffect(Unit){
        if(!Firebase.auth.currentUser?.email.isNullOrEmpty()){
            navController.navigate("home")
        }else{
            navController.navigate("login")
        }
    }
}