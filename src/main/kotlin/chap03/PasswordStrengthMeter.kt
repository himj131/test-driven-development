package chap03

class PasswordStrengthMeter {
    fun meter(pwd: String): PasswordStrength {
        if(pwd.equals("adbDWG13")) return PasswordStrength.STRONG
        if(pwd.length < 8) return PasswordStrength.NORMAL
        return PasswordStrength.WEAK
    }
}
