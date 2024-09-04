package com.example.prac

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PracController(private val pracService: pracService) {

    @GetMapping("/hello")
    fun helloWorld() :String{
        pracService.pracWith()
        return pracService.calcMinutes();

    }
}