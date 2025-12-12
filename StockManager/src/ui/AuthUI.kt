package ui

import controller.AuthController
import repository.InMemoryUserRepository
import service.AuthService

class AuthUI {

    private val userRepository = InMemoryUserRepository()
    private val authService = AuthService(userRepository)
    private val authController = AuthController(authService)

    fun start() {
        while (true) {
            println("\n=== 로그인 / 회원가입 ===")
            println("1. 회원가입")
            println("2. 로그인")
            println("0. 종료")
            print("선택: ")

            when (readln().trim()) {
                "1" -> register()
                "2" -> login()
                "0" -> return
                else -> println("잘못된 입력입니다.")
            }
        }
    }

    private fun register() {
        print("아이디 입력: ")
        val id = readln().trim()

        print("이름 입력: ")
        val name = readln().trim()

        val success = authController.register(id, name)
        if (success) {
            println("회원가입 완료!")
        } else {
            println("회원가입 실패: 이미 존재하는 아이디입니다.")
        }
    }

    private fun login() {
        print("아이디 입력: ")
        val id = readln().trim()

        val user = authController.login(id)
        if (user == null) {
            println("로그인 실패: 존재하지 않는 사용자")
        } else {
            println("${user.name}님 환영합니다! (보유 현금: ${user.cash})")
        }
    }
}
