package example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.time.LocalDate
import java.time.LocalDateTime

//        jdbcUrl =  "jdbc:mysql://10.22.65.188/amc-service-10008"
//        username = "funddbusr"
//        password = "Sh@zamb9"

//jdbcUrl = "jdbc:mysql://localhost:3306/mydb"
//username = "root"
//password = "password"

object DatabasePool {
    private val hikariConfig = HikariConfig().apply {
        jdbcUrl = "jdbc:mysql://10.22.65.215:6033/taxdb_amc_10008"
        username = "taxsupportusr"
        password = "Sh@zamb9"
        driverClassName = "com.mysql.cj.jdbc.Driver"
        maximumPoolSize = 10
        minimumIdle = 2
        idleTimeout = 30000
        connectionTimeout = 30000
    }

    private val dataSource = HikariDataSource(hikariConfig)
    fun getConnection(): Connection {
        return dataSource.connection
    }
}

fun insertUnitholderBalances(data: List<UnitholderBalance>) {
    val sql = """
        INSERT INTO unitholder_balance_tax_mf 
        (tax_id, sa_code, unitholder_id, fund_code, unit_balance, amount_balance, 
         cost_amount, nav, nav_date, unit_balance_bf, unit_balance_af, 
         cost_amount_bf, cost_amount_af, allow_redeem_unit, as_of_date) 
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """.trimIndent()

    DatabasePool.getConnection().use { conn ->
        conn.autoCommit = false
        conn.prepareStatement(sql).use { statement ->
            data.chunked(1000).forEach { chunk ->
                chunk.forEach { record ->
                    statement.setString(1, record.taxId)
                    statement.setString(2, record.saCode)
                    statement.setString(3, record.unitholderId)
                    statement.setString(4, record.fundCode)
                    statement.setDouble(5, record.unitBalance)
                    statement.setDouble(6, record.amountBalance)
                    statement.setDouble(7, record.costAmount)
                    statement.setDouble(8, record.nav)
                    statement.setDate(9, java.sql.Date.valueOf(record.navDate.toString()))
                    statement.setDouble(10, record.unitBalanceBF ?: 0.0)
                    statement.setDouble(11, record.unitBalanceAF ?: 0.0)
                    statement.setDouble(12, record.costAmountBF ?: 0.0)
                    statement.setDouble(13, record.costAmountAF ?: 0.0)
                    statement.setDouble(14, record.allowRedeemUnit ?: 0.0)
                    statement.setDate(15, java.sql.Date.valueOf(record.asOfDate.toString()))
                    statement.addBatch()
                }
                statement.executeBatch()
                conn.commit()
            }
        }
    }
}

data class UnitholderBalance(
    val taxId: String?,
    val saCode: String,
    val unitholderId: String,
    val fundCode: String,
    val unitBalance: Double,
    val amountBalance: Double,
    val costAmount: Double,
    val nav: Double,
    val navDate: LocalDate,
    val unitBalanceBF: Double?,
    val unitBalanceAF: Double?,
    val costAmountBF: Double?,
    val costAmountAF: Double?,
    val allowRedeemUnit: Double,
    val asOfDate: LocalDate?
)

fun main() {
    println("Inserting sample data... ${LocalDateTime.now()}")
    val sampleData = List(10000) { i ->
        UnitholderBalance(
            taxId = "123456789012${i % 10}",
            saCode = "SCODE$i",
            unitholderId = "UH-$i",
            fundCode = "FUND-$i",
            unitBalance = 1000.1234 + i,
            amountBalance = 10000.99 + i,
            costAmount = 5000.50 + i,
            nav = 10.1234 + i % 10,
            navDate = LocalDate.parse("2025-01-01"),
            unitBalanceBF = if (i % 2 == 0) 500.0001 + i else null,
            unitBalanceAF = if (i % 2 == 0) 2000.0002 + i else null,
            costAmountBF = if (i % 3 == 0) 9999.03 + i else null,
            costAmountAF = if (i % 3 == 0) 8888.08 + i else null,
            allowRedeemUnit = 100.1234 + i,
            asOfDate = LocalDate.parse("2025-02-10")
        )
    }

    insertUnitholderBalances(sampleData)
    println("Data inserted successfully: ${LocalDateTime.now()}")
}
