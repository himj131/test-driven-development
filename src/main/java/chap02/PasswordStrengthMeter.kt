package chap02

class PasswordStrengthMeter {
    fun meter(pwd: String?): PasswordStrength {
        if(pwd.isNullOrBlank()) return PasswordStrength.INVALID
        var meetCounts = 0
        if(lengthEnough(pwd)) meetCounts++
        var containsNum = meetsContainingNumberCriteria(pwd!!)
        if(containsNum) meetCounts++
        var containsUpp = meetsContainingUppercaseCriteria(pwd)
        if(containsUpp) meetCounts++

        if(meetCounts == 1) return PasswordStrength.WEAK
        if(meetCounts == 2) return PasswordStrength.NORMAL
        return PasswordStrength.STRONG
    }

    private fun lengthEnough(pwd: String?) = pwd!!.length >= 8

    private fun meetsContainingUppercaseCriteria (pwd: String): Boolean {
        var containsUpp = false;
        for (p in pwd.toCharArray()) {
            if (Character.isUpperCase(p)) containsUpp = true
        }
        return containsUpp
    }

    private fun meetsContainingNumberCriteria(pwd: String): Boolean {
        var containsNum = false;
        for (p in pwd.toCharArray()) {
            if (p in '0'..'9') {
                containsNum = true
                break
            }
        }
        return containsNum
    }
}
