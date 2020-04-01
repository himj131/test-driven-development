package chap03

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        return payData.billingDate.plusMonths(1)
    }
}