package com.example.simple_quiz_game.controller

import com.example.simple_quiz_game.model.request.UserRegisterRequest
import com.example.simple_quiz_game.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api")
class UserController (private val userService: UserService){

    @PostMapping("/register")
    fun userRegistration(@RequestBody newUser: UserRegisterRequest){
        try{
            userService.newUserRegistration(newUser)
        } catch (badRequest: ResponseStatusException){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exist", badRequest)
        }
    }
}