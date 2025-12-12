package service
import domain.User
import repository.UserRepository

class AuthService(
    private val userRepository: UserRepository
) {

    fun register(userId: String, name: String, initialCash: Long = 500000): Boolean {

        if (userRepository.findById(userId) != null) {
            return false
        }

        val newUser = User(
            userId = userId,
            name = name,
            cash = initialCash
        )

        userRepository.save(newUser)
        return true
    }

    fun login(userId: String): User? {
        return userRepository.findById(userId)
    }
}
