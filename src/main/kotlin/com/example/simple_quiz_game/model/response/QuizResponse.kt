package com.example.simple_quiz_game.model.response

import com.example.simple_quiz_game.model.Quiz

data class QuizResponse(
    var id: Long?,
    var title: String?,
    var text: String?,
    var options: List<String>
) {
    constructor(quiz: Quiz): this(quiz.id, quiz.title, quiz.text, quiz.options) {
    }
}