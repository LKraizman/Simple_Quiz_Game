package com.example.simple_quiz_game.configuration


import com.example.simple_quiz_game.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsImpl(private val userRepository: UserRepository) : UserDetailsService {
        @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findUserByUsername(username)
            ?: throw UsernameNotFoundException("Not found")

        return UserAdapter(user)
    }
}