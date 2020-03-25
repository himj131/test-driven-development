package chap03

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class ExpiryDateCalculatorTest {
    @Test
    internal fun 만원_납부하면_한달_뒤가_만료일임() {
        val calculator = ExpiryDateCalculator()
        var billingDate = LocalDate.of(2019,  3, 1)
        val payAmount = 10000

        var expiryDate: LocalDate = calculator.calculateExpiryDate(billingDate, payAmount)
        assertEquals(LocalDate.of(2019, 4, 1), expiryDate)
    }
}