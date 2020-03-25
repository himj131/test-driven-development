package chap03

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PasswordStrengthMeterTest {
    @Test
    internal fun meetsOnlyUpperCriteria_Then_Weak() {
        val meter = PasswordStrengthMeter()
        val result: PasswordStrength = meter.meter("zsdfDVd")
        assertEquals(PasswordStrength.WEAK, result)
    }
}