package com.example.simple_quiz_game.repository

import com.example.simple_quiz_game.model.QuizCompletion
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizCompletionRepository: JpaRepository<QuizCompletion, Long> {
    fun findAllByUserId(userId: Long?, pageable: Pageable): Page<QuizCompletion>
}