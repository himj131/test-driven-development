package chap03

import java.time.LocalDate

class ExpiryDateCalculator {
    fun calculateExpiryDate(payData: PayData): LocalDate {
        if(payData.firstBillingDate != null) {
            val candidateExp = payData.billingDate.plusMonths(1)

            if(payData.firstBillingDate!!.dayOfMonth != candidateExp.dayOfMonth) {
                return candidateExp.withDayOfMonth(payData.firstBillingDate!!.dayOfMonth)
            }
        }
        return payData.billingDate.plusMonths(1)
    }
}