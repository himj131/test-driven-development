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
        assertExpiryDate(
                PayData.builder()
                        .buillingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 4, 1)
        )

        assertExpiryDate(
                PayData.builder()
                        .buillingDate(LocalDate.of(2019,  5, 1))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 6, 1)
        )
    }

    @Test
    internal fun 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .buillingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 2, 28)
        )

        assertExpiryDate(
                PayData.builder()
                        .buillingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 6, 30)
        )

        assertExpiryDate(
                PayData.builder()
                        .buillingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2020, 2, 29)
        )
    }

    private fun assertExpiryDate(payData: PayData, expectedExpiryDate: LocalDate) {
        var realExpiryDate: LocalDate = calculator.calculateExpiryDate(payData)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}