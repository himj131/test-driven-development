package chap03

import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addedMonths = payData.payAmount / 10000
        if(payData.firstBillingDate != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths)
        } else {
            return payData.billingDate!!.plusMonths(addedMonths.toLong())
        }
    }

    private fun expiryDateUsingFirstBillingDate(payData: PayData, addedMonths: Int): LocalDate {
        val dayOfFirstBilling = payData.firstBillingDate!!.dayOfMonth
        val candidateExp = payData.billingDate!!.plusMonths(addedMonths.toLong())

        if(dayOfFirstBilling != candidateExp.dayOfMonth) {
            val dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth()
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon)
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling)
        }
        return candidateExp
    }
}