package chap03

import java.time.LocalDate
import java.time.YearMonth

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        val addedMonths = payData.payAmount / 10000
        payData.firstBillingDate?. let {
            val candidateExp = payData.billingDate!!.plusMonths(addedMonths.toLong())

            if(it.dayOfMonth != candidateExp.dayOfMonth) {
                if(YearMonth.from(candidateExp).lengthOfMonth() < it.dayOfMonth) {
                    return candidateExp.withDayOfMonth(YearMonth.from(candidateExp).lengthOfMonth())
                }
                return candidateExp.withDayOfMonth(it.dayOfMonth)
            }
        }
        return payData.billingDate!!.plusMonths(addedMonths.toLong())
    }
}