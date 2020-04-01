package chap02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun plus() {
        var result: Int = Calculator.plus(1,2)

        assertEquals(3, result)
        assertEquals(5, Calculator.plus(4,1))
    }
}