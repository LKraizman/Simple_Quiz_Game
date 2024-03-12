package com.example.simple_quiz_game.controller

import com.example.simple_quiz_game.model.request.QuizRequest
import com.example.simple_quiz_game.model.response.GameResult
import com.example.simple_quiz_game.model.response.QuizResponse
import com.example.simple_quiz_game.service.QuizService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class QuizController(private val quizService: QuizService) {


    @PostMapping("/quizzes")
    fun addNewQuiz(@RequestBody newQuiz: QuizRequest): QuizResponse {
        return quizService.saveNewQuiz(newQuiz)
    }

    @GetMapping("/quizzes")
    fun findAllQuizzes(): ArrayList<QuizResponse>{
        return quizService.getAllQuizzes()
    }

    @GetMapping("/quizzes/{id}")
    fun getQuizById(@PathVariable id: Int): QuizResponse{
        return quizService.findQuizById(id)
    }

    @PostMapping("/quizzes/{id}/solve")
    fun quizCheck(@PathVariable id: Int, @RequestParam("answer") answer: Int): GameResult {
        return quizService.checkQuizAnswer(id, answer)
    }
}