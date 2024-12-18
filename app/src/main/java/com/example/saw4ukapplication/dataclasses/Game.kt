package com.example.saw4ukapplication.dataclasses

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val development: String,
    val releaseDate: String,
)