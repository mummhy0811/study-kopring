package com.example.prac.dto

import com.example.prac.domain.UserType


//클래스가 데이터를 저장하는 역할만을 수행하면 data class 사용

data class ReqDto (
    val type: UserType,
    val email: String,
    val pw: String,
    val birth: String?
)
