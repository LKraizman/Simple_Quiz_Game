package com.example.simple_quiz_game.controller

import com.example.simple_quiz_game.model.request.QuizAnswerRequest
import com.example.simple_quiz_game.model.request.QuizRequest
import com.example.simple_quiz_game.model.response.GameResult
import com.example.simple_quiz_game.model.response.QuizResponse
import com.example.simple_quiz_game.service.QuizService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class QuizController(private val quizService: QuizService) {


    @PostMapping("/quizzes")
    @Validated
    fun addNewQuiz(@RequestBody newQuiz: QuizRequest): QuizResponse{
        return quizService.saveNewQuiz(newQuiz)
    }

    @GetMapping("/quizzes")
    fun findAllQuizzes(): List<QuizResponse>{
        return quizService.getAllQuizzes()
    }

    @GetMapping("/quizzes/{id}")
    fun getQuizById(@PathVariable id: Long): QuizResponse{
        return quizService.findQuizById(id)
    }

    @PostMapping("/quizzes/{id}/solve")
    fun quizCheck(@PathVariable id: Long,
                  @RequestBody answer: QuizAnswerRequest?): GameResult{
        return quizService.checkQuizAnswer(id, answer)
    }
}