package com.example.prac

class NewMember:Member() {
    var phone: String="010-1234-5678"

    override fun func() { // 재정의
        println("NewMember function")
        println(super.name)
        println(phone)
    }
}