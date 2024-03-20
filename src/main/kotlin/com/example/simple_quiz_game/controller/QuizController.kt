package com.example.simple_quiz_game.controller

import com.example.simple_quiz_game.model.request.QuizAnswerRequest
import com.example.simple_quiz_game.model.request.QuizRequest
import com.example.simple_quiz_game.model.response.GameResult
import com.example.simple_quiz_game.model.response.QuizCompletionResponse
import com.example.simple_quiz_game.model.response.QuizResponse
import com.example.simple_quiz_game.service.QuizService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.NoSuchElementException

@RestController
@RequestMapping("/api")
class QuizController(private val quizService: QuizService) {

    @PostMapping("/quizzes")
    @Validated
    fun addNewQuiz(@RequestBody newQuiz: QuizRequest): QuizResponse {
        return quizService.saveNewQuiz(newQuiz)
    }

    @GetMapping("/quizzes")
    fun findAllQuizzes(@RequestParam("page") page: Int): Page<QuizResponse> {
        return quizService.getAllQuizzes(page)
    }

    @GetMapping("/quizzes/{id}")
    fun getQuizById(@PathVariable id: Long): QuizResponse {
        return quizService.findQuizById(id)
    }

    @DeleteMapping("/quizzes/{id}")
    fun deleteQuizById(@PathVariable id: Long){
        return quizService.deleteQuiz(id)
    }

    @PostMapping("/quizzes/{id}/solve")
    fun quizCheck(@PathVariable id: Long,
                  @RequestBody answer: QuizAnswerRequest?): GameResult{
        try{
            return quizService.checkQuizAnswer(id, answer)
        } catch (ex: NoSuchElementException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/quizzes/completed")
    fun getQuizzesCompletions(@RequestParam("page") page: Int): Page<QuizCompletionResponse> {
        return quizService.getQuizCompletions(page)
    }
}