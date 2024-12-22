package com.example.saw4ukapplication.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saw4ukapplication.dataclasses.GamesRepository
import com.example.saw4ukapplication.screens.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SaveViewModel(private val context: Context) : ViewModel() {

    var genre by mutableStateOf("")
    var platform by mutableStateOf("")
    val data: Flow<GameData> = context.dataStore.data
        .map { preferences -> GameData(
            preferences[stringPreferencesKey("genre")] ?: "",
            preferences[stringPreferencesKey("platform")] ?: "" )
        }

    init {
        viewModelScope.launch {
            data.collect {
                genre = it.genre
                platform = it.platform
            }
        }
    }

    fun onClick(platform: String, genre : String){
        viewModelScope.launch { suspendOnClick(platform,genre) }
    }

    suspend fun suspendOnClick(platform: String, genre : String){
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey("genre")] = genre
            settings[stringPreferencesKey("platform")] = platform
        }
    }
}
