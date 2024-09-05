package com.example.prac

class NewMember(name: String, age: Int, address: String?, phone:String): Member(name, age, address) {
    var phone: String = "010-1234-5678"

    // Secondary constructor (빈 생성자)
    constructor() : this("", 0, "", "")

    override fun func() { // 재정의
        println("NewMember function")
        println(super.name)
        println(phone)
    }
}