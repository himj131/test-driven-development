package chap07

interface PasswordChecker {
    fun checkPasswordWeak(pwd: String): Boolean
}
