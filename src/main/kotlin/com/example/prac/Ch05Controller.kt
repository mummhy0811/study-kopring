package com.example.prac

import com.example.prac.domain.Book
import com.example.prac.domain.Member
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/ch05")
class Ch05Controller {

    @GetMapping("/collections")
    fun pracFilterAndMaps() {

        //filter은 원소 변환x
        var member = listOf(Member("Alice", 29), Member("Bob", 31))
        println(member.filter { it.age > 30 })
        member = member.filter { it.age > 30 }
        println(member.map { it.name }) //[Bob]

        //map은 원소 변환o. 새 컬렉션 만듦
        member = listOf(Member("Alice", 29), Member("Bob", 31))
        println(member.map { it.name }) //[Alice, Bob]

        val numbers = mapOf(0 to "zero", 1 to "one")
        println(numbers.mapValues { it.value.toUpperCase() }) //{0=ZERO, 1=ONE}


        //all 은 컬렉션의 모든 원소가 어떤 조건을 만족하는지 판단하는 연산
        val canBeInClub30 = { m: Member -> m.age <= 30 }
        println(member.all(canBeInClub30)) //false
        println(member.any(canBeInClub30)) //true


        //중첩된 컬렉션 안의 원소 처리
        val strings = listOf("abcd", "efg")
        println(strings.flatMap { it.toList() }) //[a, b, c, d, e, f, g]

        val books = listOf(
            Book("Thursday Next", listOf("Jasper Fforde")),
            Book("Mort", listOf("Terry Pratchett")),
            Book(
                "Good Omens", listOf(
                    "Terry Pratchett",
                    "Neil Gaiman"
                )
            )
        )
        //flatMap -> 배열 안의 배열을 하나의 배열로 합침(평탄화)
        println(books.flatMap { it.authors }) //[Jasper Fforde, Terry Pratchett, Terry Pratchett, Neil Gaiman]
        println(books.flatMap { it.authors }.toSet()) //[Jasper Fforde, Terry Pratchett, Neil Gaiman] //set으로 중복 제거

    }

    @GetMapping("/lazy")
    fun pracLazy() {
        val list = listOf(1, 2, 3, 4).asSequence() // 원본 컬렉션을시퀀스로 변환한다.
            .map { print("map($it) "); it * it } // 시퀀스도 컬렉션과 똑같은 API를 제공한다.
            .filter { print("filter($it) "); it % 2 == 0 }
            .toList()
        //map(1) filter(1) map(2) filter(4) map(3) filter(9) map(4) filter(16) 출력

        println(list.toString()) //필터링 되어서 [4, 16]만 남음

    }

    @GetMapping("/withAndApply")
    fun pracWithAndApply() {

        //with -> 중복된 변수명을 제거
        val result = StringBuilder()
        for (letter in 'A'..'Z') {
            result.append(letter)
        }
        result.append("\nNow I know the alphabet!")
        println(result.toString())

        val sb = StringBuilder()
        println(with(sb) {
            for (letter in 'A'..'Z') {
                this.append(letter)
            }
            append("\nNow I know the alphabet!")
            this.toString()
        })
        //with는 람다 코드를 실행한 결과를 리턴(본문의 맨 마지막에 있는 값)
        // 람다 결과 대신 수신 객체가 필요한 경우 apply 사용

        //apply는 항상 자신에게 전달된 객체를 반환
        println(alphabet(listOf("A", "B", "C")))
        println(alphabetByBuildString(listOf("A", "B", "C")))
    }

    fun alphabet(list:List<String>) = StringBuilder().apply {
        for (letter in list) {
            this.append(letter)
        }
        append("\nFinished!")
    }.toString()

    fun alphabetByBuildString(list:List<String>) = buildString {
        for (letter in list) {
            append(letter)
        }
        append("\nFinished!!")
    }

}


