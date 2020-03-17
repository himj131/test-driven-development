package chap02

class PasswordStrengthMeter {
    fun meter(pwd: String?): PasswordStrength {
        if(pwd.isNullOrBlank()) return PasswordStrength.INVALID
        if(pwd!!.length < 8) {
           return PasswordStrength.NORMAL
        }
        var containsNum = meetsContainingNumberCriteria(pwd!!)

        if(!containsNum) return PasswordStrength.NORMAL
        return PasswordStrength.STRONG
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
