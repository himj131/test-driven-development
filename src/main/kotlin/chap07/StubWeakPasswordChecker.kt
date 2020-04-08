package chap07

class StubWeakPasswordChecker: PasswordChecker {
    var weak: Boolean = true
        set(value) {value}

    override fun checkPasswordWeak(pwd: String): Boolean {
        return weak
    }
}
