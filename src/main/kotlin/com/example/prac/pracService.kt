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

    //---------0904
    fun pracWhen(a:String, b:Int):String{

        //set 인스턴스를 사용해서 비교
        // 함수가 자주 호출된다면 불필요한 가비지 객체가 늘어남

//        when(setOf(a, b)){
//            setOf("AM", 0..5) -> return "Good Night!"
//            setOf("AM", 12) -> return "Good Night!"
//            setOf("AM", 6..11) -> return "Good Morning!"
//            setOf("PM", 1..6) -> return "Good Afternoon!"
//            setOf("PM", 7..11) -> return "Good Night!"
//            setOf("PM", 12) -> return "Good Afternoon!"
//            else -> return "wrong input"
//        }

        return when(setOf(a, b)){
            setOf("AM", 0..5) -> "Good Night!"
            setOf("AM", 12) -> "Good Night!"
            setOf("AM", 6..11) -> "Good Morning!"
            setOf("PM", 1..6) -> "Good Afternoon!"
            setOf("PM", 7..11) -> "Good Night!"
            setOf("PM", 12) -> "Good Afternoon!"
            else -> "wrong input"
        }

        //대략 아래와 같은 형태로 바꾸면(직접 비교하면) 추가 객체를 만들지 않음
        //but, 가독성 떨어짐

//        when {
//            (a == RED && c2 == YELLOW) ||
//                    (c1 == YELLOW && c2 == RED) ->
//                ORANGE
//
//            (a == YELLOW && c2 == BLUE) ||
//                    (c1 == BLUE && c2 == YELLOW) ->
//                GREEN
//
//            (a == BLUE && c2 == VIOLET) ||
//                    (c1 == VIOLET && c2 == BLUE) ->
//                INDIGO
//
//            else -> throw Exception("Dirty color")
//        }



    }

    fun pracFor(a:Int, b:Int):Int{
        var sum =0

        val bigger = Math.max(a, b)
        val smaller = Math.min(a, b)

        for(i in bigger downTo smaller step 2){ //큰 수부터 작은 수까지 2씩 감소
            println(i);
            sum += i
        }
        return sum
    }


}