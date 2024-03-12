package com.example.simple_quiz_game.model.response

import com.example.simple_quiz_game.model.Quiz

data class QuizResponse (
    var id: Int,
    var title: String,
    var text: String,
    var options: ArrayList<String>
) {
    constructor(quiz: Quiz): this(quiz.quizId, quiz.title, quiz.text, quiz.options) {
    }
}