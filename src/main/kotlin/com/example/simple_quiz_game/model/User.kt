package com.example.simple_quiz_game.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User (
    var username: String? = null,
    var password: String? = null,
    var authority: String? = null,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var quizCompletions: List<QuizCompletion> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)
