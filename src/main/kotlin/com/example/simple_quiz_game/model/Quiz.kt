package com.example.simple_quiz_game.model

data class Quiz(
    var quizId: Int,
    var title: String,
    var text: String,
    var options: ArrayList<String>,
    var answer: ArrayList<Int>?
)