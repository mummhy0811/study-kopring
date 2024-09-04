package com.example.prac

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PracController(private val pracService: pracService) {

    @GetMapping("/hello")
    fun helloWorld() :String{
        pracService.pracWith()
        return pracService.calcMinutes()

    }

    //0904
    @PostMapping("/when")
    fun pracWhen(@RequestBody a:String, @RequestBody b:Int) :String{
        return pracService.pracWhen(a,b)
    }


    @PostMapping("/for")
    fun pracFor(@RequestBody a:Int, @RequestBody b:Int) :Int{
        return pracService.pracFor(a,b)
    }

    //NOTE: 확장함수 {Collection}.{확장함수명} 형태로 사용됨
    @PostMapping("/func")
    fun pracWhile(@RequestBody a:Array<String>) :String{

        return pracService.extensionProperty(a.toList().customJoinToString())
    }
}