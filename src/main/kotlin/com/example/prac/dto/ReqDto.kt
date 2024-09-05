package com.example.prac.dto

//클래스가 데이터를 저장하는 역할만을 수행하면 data class 사용

data class ReqDto (
    val id: String,
    val pw: String,
    val birth: String?
)
