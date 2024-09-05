package com.example.prac

import com.example.prac.domain.User
import com.example.prac.domain.UserType
import com.example.prac.dto.ReqDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("ch04")
class Ch04Controller {

    @PostMapping("/user")
    fun companionObject(@RequestBody user: ReqDto):String{
        val newUser:User = when(user.type){
            UserType.Email -> User.newSubscribingUser(user.email)
            UserType.Facebook -> User.newFacebookUser(123)
        }

        return newUser.name
    }
}