package chap07

class MemoryUserRepository : UserRepository {
    val users = hashMapOf<String, User>()
    override fun save(user: User) {
        users[user.id] = user
    }

    override fun findById(id: String): User? {
        return users[id]
    }
}
