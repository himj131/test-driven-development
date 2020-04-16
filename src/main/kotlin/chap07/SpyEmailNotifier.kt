package chap07

class SpyEmailNotifier : EmailNotifier {
    override var called: Boolean = false
    override lateinit var email: String

    constructor()

    override fun sendRegisterEmail(email: String) {
        this.called = true
        this.email = email
    }
}