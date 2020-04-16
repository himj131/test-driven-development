package chap07

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class UserRegisterTest {
    private lateinit var userRegister: UserRegister
    private lateinit var fakeRepository: MemoryUserRepository
    private val stubPasswordChecker = StubWeakPasswordChecker()
    private lateinit var spyEmailNotifier: EmailNotifier

    @BeforeEach
    internal fun setUp() {
        fakeRepository = MemoryUserRepository()
        spyEmailNotifier = SpyEmailNotifier()
        userRegister = UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier)
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    internal fun weakPassword() {
        stubPasswordChecker.weak = true

        assertThrows<WeakPasswordException> { userRegister.register("id", "pw", "email") }
    }

    @Test
    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    internal fun dupIdExists() {
        stubPasswordChecker.weak = false
        //given : 이미 같은 Id가 존재하는 상황 가정
        fakeRepository.save(User("id", "pw1", "emial.@mail.com"))

        assertThrows<DupIdException> {userRegister.register("id", "pwd2", "emild@mail.com")}
    }

    @Test
    @DisplayName("같은 ID가 존재하지 않으면 가입 성공")
    internal fun noDupId_RegisterSuccess() {
        stubPasswordChecker.weak = false
        userRegister.register("id", "pwd1", "email@dd.com")

        val savedUser = fakeRepository.findById("id")
        assertEquals("id", savedUser!!.id)
        assertEquals("email@dd.com", savedUser.email)
    }

    @Test
    @DisplayName("가입하면 메일을 전송함")
    internal fun whenRegisterThenSendMail() {
        stubPasswordChecker.weak = false
        userRegister.register("id", "pw", "mail")
        assertTrue (spyEmailNotifier.called)
        assertEquals("mail", spyEmailNotifier.email)
    }
}