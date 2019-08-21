package me.erikthered.whoishiring

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request

class Scraper(private val objectMapper: ObjectMapper) {
    private val http = OkHttpClient()

    fun getItem(id: Int): Item {
        val req = Request.Builder()
                .url("https://hacker-news.firebaseio.com/v0/item/$id.json")
                .build()
        val resp = http.newCall(req).execute()

        return objectMapper.readValue(resp.body!!.byteStream())
    }
}

