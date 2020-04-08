package chap07

class UserRegister(stubPasswordChecker: StubWeakPasswordChecker) {
    fun register(id: String, pwd: String, email: String) {
        throw WeakPasswordException()
    }
}