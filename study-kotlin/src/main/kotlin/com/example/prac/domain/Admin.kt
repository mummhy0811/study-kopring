package com.example.prac.domain

interface Admin {
    //email은 반드시 오버라이드, 닉네임은 필수아님
    val id:String
        get() = email.substringBefore("@")
    val email:String
}