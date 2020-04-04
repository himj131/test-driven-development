package chap03

import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addedMonths =  if(payData.payAmount == 100000) 12 else payData.payAmount / 10000
        return if(payData.firstBillingDate != null) {
            expiryDateUsingFirstBillingDate(payData, addedMonths)
        } else {
            payData.billingDate!!.plusMonths(addedMonths.toLong())
        }
    }

    private fun expiryDateUsingFirstBillingDate(payData: PayData, addedMonths: Int): LocalDate {
        val candidateExp = payData.billingDate!!.plusMonths(addedMonths.toLong())
        if(isSameDayOfMonth(payData.firstBillingDate, candidateExp)) {
            val dayOfFirstBilling = payData.firstBillingDate!!.dayOfMonth
            val dayLenOfCandiMon = lastDayOfMonth(candidateExp)
            if(dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon)
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling)
        }
        return candidateExp
    }

    private fun lastDayOfMonth(day: LocalDate?) = YearMonth.from(day).lengthOfMonth()

    private fun isSameDayOfMonth(day1: LocalDate?, day2: LocalDate) =
            day1!!.dayOfMonth != day2.dayOfMonth
}