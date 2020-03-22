package chap02

class PasswordStrengthMeter {
    fun meter(pwd: String?): PasswordStrength {
        if(pwd.isNullOrBlank()) return PasswordStrength.INVALID
        if(pwd!!.length < 8) return PasswordStrength.NORMAL
        var containsUpp = meetsContainingUppercaseCriteria(pwd)
        var containsNum = meetsContainingNumberCriteria(pwd!!)

        if(!containsUpp && !containsNum) return PasswordStrength.WEAK
        if(!containsUpp) return PasswordStrength.NORMAL
        if(!containsNum) return PasswordStrength.NORMAL
        return PasswordStrength.STRONG
    }

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
