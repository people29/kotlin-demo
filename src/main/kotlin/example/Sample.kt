package example

class Sample {
    fun sum(a: Int, b: Int): Int {
        return a + b
    }
}

fun main() {
    val totalRecord = "totalRecord"
    println(totalRecord.camelCaseToWords())
}