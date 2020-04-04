package chap03

import java.time.LocalDate

class PayData {
    var billingDate: LocalDate? = null
    var payAmount: Int = 0
    var firstBillingDate: LocalDate? = null

//    constructor(billingDate: LocalDate, payAmount: Int, firstBillingDate: LocalDate?){
//        this.billingDate = billingDate
//        this.payAmount = payAmount
//        this.firstBillingDate = firstBillingDate
//    }

    companion object Builder {
        private val data: PayData = PayData()

        fun billingDate(billingDate: LocalDate): Builder{
            data.billingDate = billingDate
            return this
        }

        fun payAmount(payAmount: Int): Builder {
            data.payAmount = payAmount
            return this
        }

        fun build():PayData {
            return data
        }

        fun builder(): Builder{
            return Builder
        }

        fun firstBillingDate(firstBillingDate: LocalDate?): Builder {
            data.firstBillingDate = firstBillingDate
            return this
        }
    }
}