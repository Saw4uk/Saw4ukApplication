package com.example.saw4ukapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.saw4ukapplication.dataclasses.Game
import com.example.saw4ukapplication.viewmodels.GameViewModel

@Composable
fun GamesListScreen(
    navController: NavController,
    gameViewModel: GameViewModel = viewModel())
{
    val games = gameViewModel.games.value ?: emptyList()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(games.size) { index ->
            val game = games[index]
            GamesListItem(game, navController, gameViewModel)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun GamesListItem(
    game: Game,
    navController: NavController,
    gameVM: GameViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                gameVM.selectMovie(game)
                navController.navigate("details/${game.id}")
            }
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = game.title,
                Modifier.size(32.dp)
            )
            Text(
                text = game.genre,
                Modifier.size(24.dp)
            )
        }
    }
}

