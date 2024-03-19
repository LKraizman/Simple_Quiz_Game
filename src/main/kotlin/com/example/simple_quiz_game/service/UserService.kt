package com.example.simple_quiz_game.service


import com.example.simple_quiz_game.model.User
import com.example.simple_quiz_game.model.request.UserRegisterRequest
import com.example.simple_quiz_game.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService (private val userRepository: UserRepository,
                   private val bCryptPasswordEncoder: BCryptPasswordEncoder){

    private val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"
    fun newUserRegistration(userCredentials: UserRegisterRequest) {
        val findUser = userRepository.checkUser(userCredentials.email)

        if (!isValidEmail(userCredentials.email) ||
            userCredentials.password.length < 5 ||
            findUser.isPresent
        ) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user credentials")
        }
        val encodedPassword = bCryptPasswordEncoder.encode(userCredentials.password)
        val newUser = User(
            userCredentials.email,
            encodedPassword,
            authority = "ROLE_USER"
        )
        userRepository.save(newUser)
    }

    fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }
}