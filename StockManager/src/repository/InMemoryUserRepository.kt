package repository

import domain.User

class InMemoryUserRepository : UserRepository {

    private val storage = mutableMapOf<String, User>()

    override fun save(user: User) {
        storage[user.userId] = user
    }

    override fun findById(id: String): User? = storage[id]
}
