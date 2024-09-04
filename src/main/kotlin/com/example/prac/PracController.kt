package com.example.prac

import org.apache.coyote.http11.Constants.a
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PracController(private val pracService: pracService) {

    @GetMapping("/hello")
    fun helloWorld() :String{
        pracService.pracWith()
        return pracService.calcMinutes();

    }

    //0904
    @GetMapping("/when")
    fun pracWhen(a:String, b:Int) :String{
        return pracService.pracWhen(a,b)
    }


    @GetMapping("/when")
    fun pracFor(a:Int, b:Int) :Int{
        return pracService.pracFor(a,b)
    }
}