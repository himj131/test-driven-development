package chap07

import com.nhaarman.mockitokotlin2.capture
import org.junit.jupiter.api.*
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.*
import org.mockito.Captor
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UserRegisterMockTest {
    lateinit var userRegister: UserRegister
    //mockk 사용시
//    private val mockWeakPasswordChecker: StubWeakPasswordChecker = mockk(relaxed = true)
    private val mockWeakPasswordChecker = mock(PasswordChecker::class.java)
    private val fakeRepository = MemoryUserRepository()
//    private val mockEmailNotifier: EmailNotifier = mockk(relaxed = true)
    private val mockEmailNotifier: EmailNotifier = mock(EmailNotifier::class.java)

    @BeforeEach
    internal fun setUp() {
        userRegister = UserRegister(mockWeakPasswordChecker, fakeRepository, mockEmailNotifier)
    }

    @Test
    @DisplayName("회원가입시 암호검사 수행")
    internal fun checkPassword() {
        userRegister.register("id", "pw", "email")
        then(mockWeakPasswordChecker)
                .should()
                .checkPasswordWeak(ArgumentMatchers.anyString())
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    internal fun weakPassword() {
        //Mokito 사용시
        given(mockWeakPasswordChecker.checkPasswordWeak(ArgumentMatchers.anyString())).willReturn(true)
        //mockk 사용시
//        every {mockWeakPasswordChecker.checkPasswordWeak(any())} returns true

        assertThrows<WeakPasswordException> { userRegister.register("id", "pw", "email") }
    }

    @Test
    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    internal fun dupIdExists() {
        //mockk 사용시
//        every {mockWeakPasswordChecker.checkPasswordWeak(any())} returns false
        //Mokito 사용시
        given(mockWeakPasswordChecker.checkPasswordWeak(ArgumentMatchers.anyString())).willReturn(false)

        //given : 이미 같은 Id가 존재하는 상황 가정
        fakeRepository.save(User("id", "pw1", "emial.@mail.com"))

        assertThrows<DupIdException> {userRegister.register("id", "pwd2", "emild@mail.com")}
    }

    @Test
    @DisplayName("같은 ID가 존재하지 않으면 가입 성공")
    internal fun noDupId_RegisterSuccess() {
        //mockk 사용시
//        every {mockWeakPasswordChecker.checkPasswordWeak(any())} returns false

        //Mokito 사용시
        given(mockWeakPasswordChecker.checkPasswordWeak(ArgumentMatchers.anyString())).willReturn(false)
        userRegister.register("id", "pwd1", "email@dd.com")

        val savedUser = fakeRepository.findById("id")
        assertEquals("id", savedUser!!.id)
        assertEquals("email@dd.com", savedUser.email)
    }

    @Test
    @DisplayName("가입하면 메일을 전송함")
    internal fun whenRegisterThenSendMail() {
        given(mockWeakPasswordChecker.checkPasswordWeak(ArgumentMatchers.anyString())).willReturn(false)
        userRegister.register("id", "pw", "email")
        val captor: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
//        then(mockEmailNotifier)
//                .should()
//                .sendRegisterEmail(captor.capture())
        verify(mockEmailNotifier).sendRegisterEmail(capture(captor))
// 아래 방법은 안됨
//        verify(mockEmailNotifier).sendRegisterEmail(captor.capture())

        val realEmail: String = captor.value
        assertEquals("email", realEmail)
    }
}