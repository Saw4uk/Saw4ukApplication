package com.example.saw4ukapplication.dataclasses

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class GamesRepository {

    init {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.freetogame.com/api/games?platform=pc")
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            println(response.body?.string())
        }
    }
}