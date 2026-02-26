package playground

import kotlinx.coroutines.*
import org.springframework.web.client.RestClient
import kotlin.concurrent.thread
import kotlin.random.Random

data class Response(val id: Int, val name: String, val age: Int, val email: String)

fun main() {
    run1()
}

private fun run1() = runBlocking {
    val startTime = System.currentTimeMillis()

    // Generate 50 random IDs
    val ids = List(12) { Random.nextInt(99999) }

    ids.chunked(12).map {
        it.map { id -> async { process2(id) } }.awaitAll().forEach(::println)
        // sleep(3000sec)
        Thread.sleep(2000)
    }


    val endTime = System.currentTimeMillis()
    println("Total running time: ${endTime - startTime} ms")
}

private fun process2(id: Int): Response? {
    return try {
        val client = RestClient.create()
        client.get()
            .uri("http://localhost:3000/data/json?id=$id")
            .retrieve()
            .body(Response::class.java)
    } catch (e: Exception) {
        println("Error processing ID $id: ${e.message}")
        null
    }
}
