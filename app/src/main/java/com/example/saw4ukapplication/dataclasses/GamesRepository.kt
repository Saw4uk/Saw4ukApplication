package com.example.saw4ukapplication.dataclasses

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object GamesRepository {

    fun get() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://www.freetogame.com/api/games")
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
                    val responseAnswer = response.body!!.string()
                }
            }
        })
    }
}


