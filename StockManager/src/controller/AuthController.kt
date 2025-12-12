package controller



import service.AuthService
import domain.User

class AuthController(
    private val authService: AuthService
) {

    fun register(userId: String, name: String): Boolean {
        return authService.register(userId, name)
    }

    fun login(userId: String): User? {
        return authService.login(userId)
    }
}
