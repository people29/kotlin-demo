package example

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class SampleTest {
    private val sample = Sample()

//    @org.junit.jupiter.api.Test // This is JUnit 5. Can be used instead of kotlin.test.Test
    @Test
    fun sum() {
        val sample = Sample()
        assertEquals(5, sample.sum(2, 3))
    }
}