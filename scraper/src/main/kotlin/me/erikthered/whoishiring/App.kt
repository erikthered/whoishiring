package me.erikthered.whoishiring

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File

fun main(args: Array<String>) {
    val objectMapper = jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

    val storyId = 20584311
    val scraper = Scraper(objectMapper)
    val posts = scraper.getItem(storyId).kids?.map { scraper.getItem(it) }
    objectMapper
            .writerWithDefaultPrettyPrinter()
            .writeValue(File("output.json"), posts)
}

