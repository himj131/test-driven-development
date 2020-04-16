package chap07

class UserRegister(private val passwordChecker: PasswordChecker,
                    private val userRepository: UserRepository,
                    private val emailNotifier: EmailNotifier) {
    fun register(id: String, pwd: String, email: String) {
        if(passwordChecker.checkPasswordWeak(pwd)) {
            throw WeakPasswordException()
        }
        val user = userRepository.findById(id)
        if(user != null) {
            throw DupIdException()
        }
        userRepository.save(User(id, pwd, email))

        emailNotifier.sendRegisterEmail(email);
    }
}