package com.example.simple_quiz_game.service

import com.example.simple_quiz_game.model.Quiz
import com.example.simple_quiz_game.model.request.QuizAnswerRequest
import com.example.simple_quiz_game.model.request.QuizRequest
import com.example.simple_quiz_game.model.response.GameResult
import com.example.simple_quiz_game.model.response.QuizResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class QuizService {

    private var quizBase: ArrayList<Quiz> = ArrayList()
    private var quizId: Int = 0

    fun saveNewQuiz(quizRequest: QuizRequest): QuizResponse{
        if(quizRequest.text.isEmpty()
            || quizRequest.title.isEmpty()
            || quizRequest.options.size < 2){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
        quizId += 1
        val quiz = Quiz(
            quizId,
            quizRequest.title,
            quizRequest.text,
            quizRequest.options,
            quizRequest.answer
        )
        quizBase.add(quiz)
        return QuizResponse(quiz)
    }

    fun getAllQuizzes(): ArrayList<QuizResponse>{
        val quizResponseList: ArrayList<QuizResponse> = ArrayList()
        quizBase.forEach { quiz ->
            quizResponseList.add(
                QuizResponse(
                    quiz.quizId,
                    quiz.title,
                    quiz.text,
                    quiz.options))
        }
        return  quizResponseList
    }

    fun findQuizById(quizId: Int): QuizResponse {
        try {
            return QuizResponse(quizBase[quizId-1])
        } catch (ex: IndexOutOfBoundsException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found")
        }

    }

    fun checkQuizAnswer(quizId: Int, answer: QuizAnswerRequest?): GameResult{
        try{
            val actualQuiz: Quiz = quizBase[quizId-1]
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