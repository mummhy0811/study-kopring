package com.example.prac.domain

open class Member(
    var name: String = "",
    var age: Int = 0,
    var address: String? = ""
) {
    // Secondary constructor (빈 생성자)
    constructor() : this("", 0, "")

    open fun func(){
        println("Parent function")
    }
}
