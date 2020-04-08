package chap07

class UserRegister(private val passwordChecker: PasswordChecker) {
    fun register(id: String, pwd: String, email: String) {
        if(passwordChecker.checkPasswordWeak(pwd)) {
            throw WeakPasswordException()
        }
    }
}