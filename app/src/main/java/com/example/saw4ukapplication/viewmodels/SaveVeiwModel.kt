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
import com.example.saw4ukapplication.screens.dataStore
import kotlinx.coroutines.launch

class SaveViewModel(private val context: Context) : ViewModel() {

    var genre by mutableStateOf("")
    var platform by mutableStateOf("")

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
