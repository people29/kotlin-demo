package playground

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.web.client.RestClient
import kotlin.random.Random


fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    // Generate 50 random IDs
    val ids = List(12) { Random.nextInt(99999) }

    // Process IDs in chunks of 10
    ids.chunked(5).forEach { chunk ->
        processChunk(chunk).map { println(it) }
        println("Completed batch of ${chunk.size} IDs")
    }

    val endTime = System.currentTimeMillis()
    println("Total running time: ${endTime - startTime} ms")
}

private suspend fun processChunk(ids: List<Int>) = coroutineScope {
    ids.map { id ->
        println("id: $id")
        async {
            process(id)
        }
    }.awaitAll()
}

private fun process(id: Int): Response? = try {
    val client = RestClient.create()
    client.get()
        .uri("http://localhost:3000/data/json?id=$id")
        .retrieve()
        .body(Response::class.java)
} catch (e: Exception) {
    println("Error processing ID $id: ${e.message}")
    null
}