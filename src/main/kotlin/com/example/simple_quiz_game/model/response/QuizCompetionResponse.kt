package com.example.simple_quiz_game.model.response

import java.time.LocalDateTime

data class QuizCompletionResponse (
    var id: Long? = null,
    var completedAt: LocalDateTime? = null
)
