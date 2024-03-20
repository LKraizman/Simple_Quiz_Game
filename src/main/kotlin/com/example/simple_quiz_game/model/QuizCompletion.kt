package com.example.simple_quiz_game.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class QuizCompletion (
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    var quiz: Quiz? = null,

    var completedAt: LocalDateTime? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var completionId: Long? = null
)