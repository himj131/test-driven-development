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

    @Test
    internal fun 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(LocalDate.of(2019, 1, 31), 10000,
                                LocalDate.of(2019, 2, 28))
        assertExpiryDate(LocalDate.of(2019, 5, 31), 10000,
                LocalDate.of(2019, 6, 30))
        assertExpiryDate(LocalDate.of(2020, 1, 31), 10000,
                LocalDate.of(2020, 2, 29))
    }

    private fun assertExpiryDate(billingDate: LocalDate, payAmount: Int, expectedExpiryDate: LocalDate) {
        var realExpiryDate: LocalDate = calculator.calculateExpiryDate(billingDate, payAmount)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}