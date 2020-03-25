package chap03

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PasswordStrengthMeterTest {
    @Test
    internal fun meetsOnlyUpperCriteria_Then_Weak() {
        val meter = PasswordStrengthMeter()
        val result: PasswordStrength = meter.meter("zsdDfDVd")
        assertEquals(PasswordStrength.WEAK, result)
    }

    @Test
    internal fun meetsAllCriteria_Then_Weak() {
        val meter = PasswordStrengthMeter()
        val result = meter.meter("adbDWG13")
        assertEquals(PasswordStrength.STRONG, result)
//        var result2 = meter.meter("zXcke143")
//        assertEquals(PasswordStrength.STRONG, result2)
    }

    @Test
    internal fun meetsOtherCriteria_except_for_Length_Then_Normal() {
        val meter = PasswordStrengthMeter()
        val result = meter.meter("s1@sd")
        assertEquals(PasswordStrength.NORMAL, result)
        val result2 = meter.meter("sdk14")
        assertEquals(PasswordStrength.NORMAL, result2)
    }


}