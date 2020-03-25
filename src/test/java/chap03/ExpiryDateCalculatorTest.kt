package chap03

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class ExpiryDateCalculatorTest {
    private lateinit var calculator: ExpiryDateCalculator

    @BeforeEach
    internal fun setUp() {
        calculator = ExpiryDateCalculator()
    }

    @Test
    internal fun 만원_납부하면_한달_뒤가_만료일임() {
        assertExpiryDate(LocalDate.of(2019, 3, 1), 10000,
                            LocalDate.of(2019, 4, 1))

        assertExpiryDate(LocalDate.of(2019,  5, 1), 10000,
                            LocalDate.of(2019, 6, 1))
    }

    private fun assertExpiryDate(billingDate: LocalDate, payAmount: Int, expectedExpiryDate: LocalDate) {
        var realExpiryDate: LocalDate = calculator.calculateExpiryDate(billingDate, payAmount)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}