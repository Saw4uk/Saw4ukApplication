package com.example.saw4ukapplication.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.saw4ukapplication.viewmodels.GameViewModel
import java.net.URL
import kotlin.concurrent.thread

@Composable
fun DetailsScreen(
    movieId: Int,
    navController: NavController,
    gameVM: GameViewModel = viewModel()
) {
    val game = gameVM.games.value?.firstOrNull { it.id == movieId }

    if (game != null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Назад")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${game.title}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = rememberAsyncImagePainter(model = game.thumbnail),
                    contentDescription = game.title,
                    modifier = Modifier.fillMaxWidth().height(250.dp).padding(8.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Жанр: ${game.genre}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Разработчик: ${game.developer}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Издатель: ${game.publisher}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Платформа: ${game.platform}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Ссылка на профиль игры: ${game.freetogame_profile_url}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Описание: ${game.short_description}")
            }
        }
    }
}
