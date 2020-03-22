package chap02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordStrengthMeterTest {
    val meter = PasswordStrengthMeter()

    @Test
    internal fun meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG)

    }

    @Test
    internal fun meetsOtherCriteria_Except_for_Length_Then_Normal() {
        assertStrength("ab12!A", PasswordStrength.NORMAL)
    }

    @Test
    internal fun meetsOtherCriteria_Except_for_number_Then_Normal() {
        assertStrength("ab!ALBKDaa", PasswordStrength.NORMAL)
    }


    private fun assertStrength(password: String?, expStr: PasswordStrength) {
        val strength = meter.meter(password)
        assertEquals(expStr, strength)
    }

    @Test
    internal fun nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID)
    }

    @Test
    internal fun emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID)
    }

    @Test
    internal fun meetsOtherCriteria_except_for_UppperCase_Then_Normal() {
        assertStrength("123asdf!!", PasswordStrength.NORMAL)
    }

    @Test
    internal fun meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("aaaaaaaaa", PasswordStrength.WEAK)
    }

    @Test
    internal fun meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK)
    }
}