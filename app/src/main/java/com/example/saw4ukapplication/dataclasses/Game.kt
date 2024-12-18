package com.example.saw4ukapplication.dataclasses

import kotlinx.serialization.Serializable
import org.json.JSONObject

@Serializable
data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String,
    val game_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val release_date: String,
    val freetogame_profile_url: String
)


class Game2(json: String) : JSONObject(json) {

}