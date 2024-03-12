package com.example.simple_quiz_game.controller

import com.example.simple_quiz_game.model.GameResult
import com.example.simple_quiz_game.model.Quiz
import org.springframework.web.bind.annotation.*

@RestController

@RequestMapping("/api/quiz")
class QuizController {
    @GetMapping
    fun quizRequest(): Quiz {
        val title = "The Porsche Logo"
        val text = "What is depicted on the Porsche logo?"
        val optionsList = ArrayList<String>()
        optionsList.add("Horse")
        optionsList.add("Bull")
        optionsList.add("Elephant")
        optionsList.add("Eagle")
        return Quiz(title, text, optionsList)
    }

    @PostMapping("quizCheck")
    fun quizCheck(@RequestParam("answer") answer: Int): GameResult {
        return if (answer != 2) {
            val fail = false
            val failFeedback = "Wrong answer! Please, try again."
            GameResult(fail, failFeedback)
        } else {
            val success = true
            val successFeedback = "Congratulations, you're right!"
            GameResult(success, successFeedback)
        }
    }
}