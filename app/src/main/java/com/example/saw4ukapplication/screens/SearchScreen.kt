@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.saw4ukapplication.screens

import android.annotation.SuppressLint
import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.saw4ukapplication.MainActivity
import com.example.saw4ukapplication.viewmodels.GameViewModel
import com.example.saw4ukapplication.viewmodels.SaveViewModel

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    saveVM: SaveViewModel
){
    Scaffold {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Text("SearchScreen", modifier = Modifier.padding(24.dp).height(20.dp), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text("Enter the game genre: Shooter, MMORPG, Strategy, Social", modifier = Modifier.height(20.dp))
                TextField(value = saveVM.genre, onValueChange = {saveVM.genre = it})
                Spacer(modifier = Modifier.height(10.dp))
                Text("Enter the game platform: Pc, Browser, all", modifier = Modifier.height(20.dp))
                TextField(value = saveVM.platform, onValueChange = {saveVM.platform = it})
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    saveVM.onClick(saveVM.platform,saveVM.genre)
                },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color(0xff004D40),       // цвет текста
                        containerColor = Color(0xff9ed6df))     // цвет фона
                ){ Text("Confirm", fontSize = 28.sp) }
            }
        }
    }
}