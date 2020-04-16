package chap07

class StubWeakPasswordChecker: PasswordChecker {
    var weak: Boolean = true

    override fun checkPasswordWeak(pwd: String): Boolean {
        return weak
    }
}
