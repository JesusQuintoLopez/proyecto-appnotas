package com.chunmaru.appnotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chunmaru.appnotas.navigation.NavManager
import com.chunmaru.appnotas.ui.theme.AppNotasTheme
import com.chunmaru.appnotas.viewModels.LoginViewModel
import com.chunmaru.appnotas.viewModels.NotesViewModel
import com.chunmaru.appnotas.views.login.TabsView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val loginViewModel:LoginViewModel by viewModels()
        val notesViewModel:NotesViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            AppNotasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(loginViewModel,notesViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppNotasTheme {
        Greeting("Android")
    }
}