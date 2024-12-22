package com.example.saw4ukapplication.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saw4ukapplication.dataclasses.Game
import com.example.saw4ukapplication.dataclasses.GamesRepository
import com.example.saw4ukapplication.screens.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

data class GameData(var genre : String = "",
                  var platform : String = "")


class GameViewModel(context: Context) : ViewModel() {
    val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games
    val data: Flow<GameData> = context.dataStore.data
        .map { preferences -> GameData(
            preferences[stringPreferencesKey("genre")] ?: "",
            preferences[stringPreferencesKey("platform")] ?: "" )
        }
    var genre : String = ""
    var platform : String = ""

    private val _chosedGame = mutableStateOf<Game?>(null)
    companion object {
        private val _chosedGameID = MutableLiveData<Int?>(null)
        val chosedMovieID: LiveData<Int?> get() = _chosedGameID
    }

    init {
         viewModelScope.launch {
             data.collect {
                 genre = it.genre
                 platform = it.platform
                 GamesRepository.getGames(
                     parseGenreString(genre),
                     parsePlatformString(platform))
                 _games.value = GamesRepository.games
             }

         }

    }

    fun parsePlatformString(string: String) : String{
        if(string == "") return "?platform=all";
        return "?platform=${string.lowercase()}";
    }

    fun parseGenreString(string: String) : String{
        if(string == "") return "";
        return "&category=${string}";
    }

    fun selectMovie(movie: Game) {
        _chosedGame.value = movie
        _chosedGameID.value = movie.id
    }
}