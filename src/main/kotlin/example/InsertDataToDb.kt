package example

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.time.LocalDateTime
import kotlin.random.Random

// Database Configuration
//const val DB_URL =  "jdbc:mysql://10.22.65.188/amc-service-10008"
//const val DB_USER = "funddbusr"
//const val DB_PASSWORD = "Sh@zamb9"

const val DB_URL = "jdbc:mysql://localhost:3306/mydb"
const val DB_USER = "root"
const val DB_PASSWORD = "password"

// Function to generate random data lazily using Sequence
fun generateUnitholderData(): Sequence<List<Any>> = sequence {
    val asOfDate = java.sql.Date.valueOf("2025-03-14")
    for (i in 1..1_500_000) {
        yield(
            listOf(
                randomTaxId(),
                randomSaCode(),
                randomUnitholderId(),
                fundCode(),
                10_000.0081, // unit_balance
                22_999.88, // amount_balance
                999990.99, // cost_amount
                888.9900, // nav
                asOfDate, // nav_date
                0.0, // unit_balance_bf
                0.0, // unit_balance_af
                0.0, // cost_amount_bf
                0.0, // cost_amount_af
                1000.00, // allow_redeem_unit
                asOfDate, // as_of_date
            )
        )
    }
}

// Function to batch insert into MySQL
fun insertUnitholderData() {
    val connection: Connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)
    connection.autoCommit = false

    val sql = """
        INSERT INTO unitholder_balance_tax_mf (tax_id, sa_code, unitholder_id, fund_code, unit_balance, 
            amount_balance, cost_amount, nav, nav_date, unit_balance_bf, unit_balance_af, 
            cost_amount_bf, cost_amount_af, allow_redeem_unit, as_of_date) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """.trimIndent()

    val preparedStatement: PreparedStatement = connection.prepareStatement(sql)

    generateUnitholderData()
        .chunked(1000)
        .forEach { batch ->
            batch.forEach { row ->
                row.forEachIndexed { index, value ->
                    preparedStatement.setObject(index + 1, value)
                }

                preparedStatement.addBatch()
            }
            preparedStatement.executeBatch()
            connection.commit() // Commit batch
        }

    preparedStatement.close()
    connection.close()
    println("Insert successfully!")
}

// 🔹 Helper Functions to Generate Random Data
fun randomTaxId(): String = (1..13).joinToString("") { Random.nextInt(0, 9).toString() }
fun randomSaCode(): String = "SA-${Random.nextInt(10, 100)}"
fun randomUnitholderId(): String = "UH-${Random.nextInt(1, 9999999)}"
fun fundCode() = "FUNDCODE-${Random.nextInt(1, 9999999)}"
fun randomDecimal(min: Double, max: Double, precision: Int): Double =
    String.format("%.${precision}f", Random.nextDouble(min, max)).toDouble()

// Run the insert function
fun main() {
    println("start ${LocalDateTime.now()}")
    insertUnitholderData()
    println("end ${LocalDateTime.now()}")
}
