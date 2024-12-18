package com.example.saw4ukapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import com.example.saw4ukapplication.screens.GamesListScreen
import com.example.saw4ukapplication.screens.DetailsScreen
import com.example.saw4ukapplication.screens.SearchScreen
import com.example.saw4ukapplication.viewmodels.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        NavHost(navController, startDestination = "gamesList", Modifier.padding(padding)) {
            composable("gamesList") { GamesListScreen(navController) }
            composable("details/{gameId}") { backStackEntry ->
                val gameId = backStackEntry.arguments?.getString("gameId")?.toInt() ?: 1
                DetailsScreen(gameId, navController)
            }
            composable("searchScreen") { SearchScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Menu, contentDescription = "Games") },
            label = { Text("Игры") },
            selected = navController.currentDestination?.route == "gamesList",
            onClick = {
                navController.navigate("gamesList") {
                    popUpTo("gamesList") { saveState = true }
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Подробнее") },
            label = { Text("Подробнее") },
            selected = navController.currentDestination?.route == "details",
            onClick = {
                GameViewModel.chosedMovieID.value?.let { currentMovieId ->
                    navController.navigate("details/$currentMovieId")
                } ?: run {
                    navController.navigate("details/1")
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Информация") },
            label = { Text("Информация") },
            selected = navController.currentDestination?.route == "searchScreen",
            onClick = {
                navController.navigate("searchScreen")
            }
        )
    }
}