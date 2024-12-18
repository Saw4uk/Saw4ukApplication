package com.example.saw4ukapplication.dataclasses

import org.json.JSONObject

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

class Game2(json: String) : JSONObject(json) {
    val id = this.optInt("id")
    val title: String? = this.optString("title")
    val description: String? = this.optString("description")
    val genre: String? = this.optString("genre")
    val platform: String? = this.optString("platform")
    val publisher: String? = this.optString("publisher")
    val development: String? = this.optString("development")
    val releaseDate: String? = this.optString("releaseDate")
}