package com.example.simple_quiz_game.model.request

data class QuizRequest (
    val title: String,
    val text: String,
    val options: ArrayList<String>,
    var answer: ArrayList<Int>?
)