package me.erikthered.whoishiring

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {

    val logger = LoggerFactory.getLogger("scraper")

    val objectMapper = jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

    val itemsFile = File("items.json")
    if (!itemsFile.exists()) {
        val storyId = 20584311
        val scraper = Scraper(objectMapper)
        val posts = scraper.getItem(storyId).kids?.map { scraper.getItem(it) }
        objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(itemsFile, posts)
    }

    val items: List<Item> = objectMapper.readValue(itemsFile)

    val jobPostingsFile = File("job_postings.json")

    val jobs: List<JobPosting> = items.mapNotNull {
        val fields = it.text?.split("|") ?: emptyList()
        if (fields.size > 1) {
            JobPosting(id = it.id, company = fields[0].trim(), fullText = it.text ?: "error")
        } else {
            logger.error("Unhandled post format: ${it.text}")
            null
        }
    }

    objectMapper
            .writerWithDefaultPrettyPrinter()
            .writeValue(jobPostingsFile, jobs)
}

