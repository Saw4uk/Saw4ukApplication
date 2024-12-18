package com.example.saw4ukapplication.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saw4ukapplication.dataclasses.Game
import com.example.saw4ukapplication.dataclasses.GamesDataBase
import com.example.saw4ukapplication.dataclasses.GamesRepository

class GameViewModel : ViewModel() {
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    private val _chosedGame = mutableStateOf<Game?>(null)
    val selectedGame: Game? get() = _chosedGame.value
    companion object {
        private val _chosedGameID = MutableLiveData<Int?>(null)
        val chosedMovieID: LiveData<Int?> get() = _chosedGameID
    }

    init {
        _games.value = GamesDataBase.games
        val x = GamesRepository.get()
    }

    fun selectMovie(movie: Game) {
        _chosedGame.value = movie
        _chosedGameID.value = movie.id
    }
}