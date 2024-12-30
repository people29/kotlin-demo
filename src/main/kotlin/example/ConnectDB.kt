package example

import java.sql.Connection
import java.sql.DriverManager
import java.time.LocalDateTime

fun randomAph(amt: Int = 1): String {
    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var result = ""
    for (i in 1..amt) {
        result += alphabet[(0 until alphabet.length).random()].toString()
    }
    return result
}

fun randomNum(amt: Int = 1): String {
    val numbers = "0123456789"
    var result = ""
    for (i in 1..amt) {
        result += numbers[(0 until numbers.length).random()].toString()
    }
    return result
}

fun randomFundCode(): String {
    return randomAph(5) + randomNum(3)
}

fun insert() {
    val url = "jdbc:postgresql://localhost:5432/postgres"
    val username = "postgres"
    val password = "password"

    try {
        val connection: Connection = DriverManager.getConnection(url, username, password)
        val statement = connection.createStatement()

        // table fund(id, code, name, nav)
        // table orders(id, investor_id, fund_id, amount, status, created_at, updated_at, created_by, updated_by)

        for (i in 1..250000) {
            val investor_id = (1..10).random()
            val fund_id = (1..250).random()
            val amount = (1000..100000).random()
            val status = listOf("pending", "approved", "cancelled").random()
            val created_at = LocalDateTime.now().plusDays((1..100).random().toLong())
            val created_by = listOf("somsri", "angsumarin", "siriporn", "jakkrapong", "panlop", "sudthida", "prapasri").random()

            val sql = "INSERT INTO orders (investor_id, fund_id, amount, status, created_at, created_by) VALUES ($investor_id, $fund_id, $amount, '$status', '$created_at', '$created_by')"

            statement.executeUpdate(sql)
        }

        statement.close()
        connection.close()
    } catch (e: Exception) {
        println(e)
        e.printStackTrace()
    }
}

fun main() {
    insert()
}