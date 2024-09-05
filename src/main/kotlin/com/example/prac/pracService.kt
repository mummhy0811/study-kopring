package com.example.prac

import org.apache.coyote.http11.Constants.a
import org.springframework.data.jpa.domain.AbstractPersistable_.id
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
            println(i)
            sum += i
        }
        return sum
    }

    var StringBuilder.lastChar: Char
        get() = get(length - 1)
        set(value: Char) {
            this.setCharAt(length - 1, value)
        }

    fun extensionProperty(s:String):String{
        println("~~~")
        val sb = StringBuilder(s)
        sb.lastChar = '~'
        println(sb.toString())
        return sb.toString()
    }

    //0905
    fun arrAndMap(args:Array<String>):List<String>{
        val list = listOf("args: ", *args) //자바와 달리 스프레드 연산자를 이용해 넘겨줘야 함(가변길이 인자를 넘기는 경우)
        println(list)

        //map을 만들 땐 mapOf사용
        //1.to("one")또는 1 to "one" 형태로 호출
        val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
        println(map)

        return list
    }


    fun pracFunc2(){
        println(1 customAdd 2)

        val member = NewMember("John", 33, "seoul", "010-1234-5678")
        saveUser(member)
        member.saveMember() //확장함수
    }
    //중위연산자
    // 함수를 중위 호출에 사용하도록 허옹하고 싶으면 infix키워드 사용
    infix fun Int.customAdd(x: Int): Int = (this+x)*2

    //꼬리재귀함수
//    tailrec fun reccursive(n:Int, sum:Int = 0):Int{
//        //tailrec 키워드를 붙이면 컴파일러가 꼬리재귀 최적화를 수행
//        // 즉, 스택 오버플로우를 방지할 수 있음
//        if(n==0) return sum
//        else return reccursive(n-1, sum+n)
//    }

    //중첩 함수
    /*
    fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Name")
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Address")
    }

    // Save user to the database
}
     */

    fun saveUser(user: NewMember) {
        fun validate(value: String, fieldName: String) { // user 파라미터를 중복 사용하지 않는다.
            if (value.isEmpty()) {
                throw IllegalArgumentException(
                    "Can't save user ${user.name}: " + // 바깥 함수의 파라미터에 직접 접근할 수 있다.
                            "empty $fieldName")
            }
        }

        validate(user.name, "Name")
        user.address?.let { validate(it, "Address") }

        // Save user to the database
    }

    //클래스를 확장한 함수
    fun NewMember.saveMember() {
        fun validate(value: String, fieldName: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException(
                    "Can't save user $name: empty $fieldName")
            }
        }

        validate(name, "Name")
        address?.let { validate(it, "Address") }
    }


}