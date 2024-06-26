package com.example.simple_quiz_game.model

import jakarta.persistence.*
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode

@Entity
class Quiz(
    var title: String = "",

    var text: String = "",

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    var options: List<String> = listOf(),

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    var answer: List<Int>? = listOf(),

    @OneToMany(fetch = FetchType.EAGER,
        mappedBy = "quiz",
        cascade = [CascadeType.ALL])
    val completions: List<QuizCompletion> = listOf(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)
