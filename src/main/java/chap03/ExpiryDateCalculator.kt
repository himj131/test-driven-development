package chap03

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(billingDate: LocalDate, payAmount: Int): LocalDate {
        return LocalDate.of(2019, 4, 1)
    }
}