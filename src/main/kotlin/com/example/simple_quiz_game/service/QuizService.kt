package com.example.simple_quiz_game.service

import com.example.simple_quiz_game.model.Quiz
import com.example.simple_quiz_game.model.request.QuizAnswerRequest
import com.example.simple_quiz_game.model.request.QuizRequest
import com.example.simple_quiz_game.model.response.GameResult
import com.example.simple_quiz_game.model.response.QuizResponse
import com.example.simple_quiz_game.repository.QuizRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.NoSuchElementException

@Service
class QuizService (private val quizRepository: QuizRepository){

    fun saveNewQuiz(quizRequest: QuizRequest): QuizResponse{
        if(quizRequest.text.isEmpty()
            || quizRequest.title.isEmpty()
            || quizRequest.options.size < 2){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
        val quiz = Quiz(quizRequest.title,
            quizRequest.text,
            quizRequest.options,
            quizRequest.answer)
        quizRepository.save(quiz)
        return QuizResponse(quiz)
    }

    fun getAllQuizzes(): List<QuizResponse> {
        return quizRepository.findAll().map {
            QuizResponse(it.id, it.title, it.text, it.options) }
    }

    fun findQuizById(quizId: Long): QuizResponse {
        try {
            return QuizResponse(quizRepository.findById(quizId).get())
        } catch (ex: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found")
        }

    }

    fun checkQuizAnswer(quizId: Long, answer: QuizAnswerRequest?): GameResult{
        try{
            val actualQuiz: Quiz = quizRepository.findById(quizId).get()
            return if (isEqualIgnoreOrder(actualQuiz.answer, answer?.answer)
                || actualQuiz.answer == null && answer?.answer?.isEmpty() == true
            ) {
                val success = true
                val successFeedback = "Congratulations, you're right!"
                GameResult(success, successFeedback)
            } else {
                val fail = false
                val failFeedback = "Wrong answer! Please, try again."
                GameResult(fail, failFeedback)
            }
        } catch (ex: IndexOutOfBoundsException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found")
        }
    }

    fun isEqualIgnoreOrder(x: List<Int>?, y: List<Int>?): Boolean {
        if (x == y) {
            return true
        }
        if (x == null || y == null || x.size != y.size) {
            return false
        }
        return x.sorted().toList() == y.sorted().toList()
    }
}