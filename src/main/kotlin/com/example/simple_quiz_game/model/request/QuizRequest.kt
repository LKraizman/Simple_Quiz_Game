package com.example.simple_quiz_game.model.request

data class QuizRequest (
    var title: String,
    var text: String,
    var options: ArrayList<String>,
    var answer: Int
)