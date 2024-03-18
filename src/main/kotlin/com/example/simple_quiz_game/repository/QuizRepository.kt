package com.example.simple_quiz_game.repository

import com.example.simple_quiz_game.model.Quiz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizRepository: JpaRepository<Quiz, Long> {
}