package repository

import domain.User

interface UserRepository {
    fun save(user: User)
    fun findById(id: String): User?
}