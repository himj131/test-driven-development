package chap03

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(billingDate: LocalDate, payAmount: Int): LocalDate {
        return billingDate.plusMonths(1)
    }
}