package com.example.saw4ukapplication.dataclasses

import android.content.Context
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object GamesRepository {

    val games = mutableListOf<Game>()

//

    fun getGames(genreString: String, platformString: String){
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://www.freetogame.com/api/games${platformString}${genreString}")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Запрос к серверу не был успешен:" + " ${response.code} ${response.message}")
                    }
                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    games.clear()
                    //Добил как смог, наверняка есть библиотеки, которые не заставляют так извиваться, но мне кажется этого достаточно
                    val y = response.body!!.string().replace("[","").replace("]","").substring(1)
                    y.split("},{").forEach { z ->
                        games.add(Json.decodeFromString<Game>("{$z}"))
                        if(games.count() >= 20)
                            return
                    }

                }
            }
        })
    }
}


