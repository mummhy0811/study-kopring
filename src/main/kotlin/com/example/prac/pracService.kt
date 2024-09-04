package com.example.prac

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class pracService {
    fun calcMinutes():String{
        val ld = LocalDateTime.now()
        when (ld.minute) {
            in 0..15 -> return "Good Morning!"
            in 16..29 -> return "Good Afternoon!"
            else -> return "Good Evening!"
        }
    }

    fun pracWith(){

        println("~~ prac with")
        val user1 = Member()
        with(user1) {
            name = "John Doe"
            age = 30
            address = "123 Main St"
        }

        val user2 = NewMember()
        user2.apply {
            name = "Jane Doe"
            age = 28
            address = "456 Elm St"
        }

        user1.func()
        println("~~~~~~~~~")
        user2.func()

        /*
        ~~ prac with
        Parent function
        ~~~~~~~~~
        NewMember function
        Jane Doe
        010-1234-5678
         */
    }
}