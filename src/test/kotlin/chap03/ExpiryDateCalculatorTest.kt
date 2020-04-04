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
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 4, 1)
        )

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,  5, 1))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 6, 1)
        )
    }

    @Test
    internal fun 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 2, 28)
        )

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2019, 6, 30)
        )

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2020, 2, 29)
        )
    }

    @Test
    internal fun 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        var payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10000)
                .build()
        assertExpiryDate(payData, LocalDate.of(2019, 3, 31))

        var payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,30))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10000)
                .build()
        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30))
    }

    @Test
    internal fun 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .payAmount(20000)
                        .build(), LocalDate.of(2019,5,1)
        )

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .payAmount(30000)
                        .build(), LocalDate.of(2019,6,1)
        )
    }

    @Test
    internal fun `첫 납부일과 만료일 일자가 다를때 이만원 이상 납부`() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019,1,31))
                        .billingDate(LocalDate.of(2019,2,28))
                        .payAmount(20000)
                        .build(), LocalDate.of(2019,4,30)
        )

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019,3,31))
                        .billingDate(LocalDate.of(2019,4,30))
                        .payAmount(30000)
                        .build(), LocalDate.of(2019,7,31)
        )
    }

    private fun assertExpiryDate(payData: PayData, expectedExpiryDate: LocalDate) {
        var realExpiryDate: LocalDate = calculator.calculateExpiryDate(payData)
        assertEquals(expectedExpiryDate, realExpiryDate)
    }
}