package chap07

interface EmailNotifier {
    val email: String
    val called: Boolean

    fun sendRegisterEmail(email: String)
}