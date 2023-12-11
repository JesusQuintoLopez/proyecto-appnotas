package com.chunmaru.appnotas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chunmaru.appnotas.viewModels.LoginViewModel
import com.chunmaru.appnotas.viewModels.NotesViewModel
import com.chunmaru.appnotas.views.login.BlankView
import com.chunmaru.appnotas.views.login.TabsView
import com.chunmaru.appnotas.views.notes.AddNoteView
import com.chunmaru.appnotas.views.notes.EditNoteView
import com.chunmaru.appnotas.views.notes.HomeView

@Composable
fun NavManager(loginVM: LoginViewModel, notesVM: NotesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "blank") {
        composable("blank") {
            BlankView(navController)
        }

        composable("login") {
            TabsView(navController, loginVM)
        }

        composable(route = "home") {
            HomeView(navController, notesVM)
        }
        composable(route = "addNote") {
            AddNoteView(navController, notesVM)
        }
        composable(
            route = "editNote/{idDoc}",
            arguments = listOf(
                navArgument("idDoc") { type = NavType.StringType }
            )
        ) {
            val idDoc = it.arguments?.getString("idDoc") ?: ""
            EditNoteView(navController, notesVM, idDoc)
        }
    }
}