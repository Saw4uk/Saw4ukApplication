package com.example.saw4ukapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saw4ukapplication.ui.theme.Saw4ukApplicationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Saw4ukApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background)
                {
                    val navController = rememberNavController()
                    val bottomItems = listOf("list","search","push")
                    Scaffold(bottomBar = {
                        BottomNavigation {
                            bottomItems.forEach{ splashScreen ->
                                BottomNavigationItem(
                                    selected = false,
                                    onClick = {
                                        navController.navigate(splashScreen)
                                    },
                                    label = { Text(splashScreen) },
                                    icon = {})
                            }
                        }
                    }
                    )
                    {
                        NavHost(navController = navController, startDestination = "list")
                        {
                            composable("list") {Text("list", modifier = Modifier.padding(24.dp))}
                            composable("search") {Text("search", modifier = Modifier.padding(24.dp))}
                            composable("push") {Text("push", modifier = Modifier.padding(24.dp))}
                        }
                    }
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
    Saw4ukApplicationTheme {
        Greeting("Android")
    }
}