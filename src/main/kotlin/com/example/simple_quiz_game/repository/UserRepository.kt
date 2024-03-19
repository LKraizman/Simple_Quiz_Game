package com.example.simple_quiz_game.repository

import com.example.simple_quiz_game.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository: CrudRepository<User, Long> {
    fun findUserByUsername(username: String): User?

    @Query("SELECT u FROM User u WHERE u.username = :username")
    fun checkUser(@Param("username") username: String): Optional<User?>
}