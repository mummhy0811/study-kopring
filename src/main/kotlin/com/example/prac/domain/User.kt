package com.example.prac.domain

//동반 객체 - 자바의 정적 메서드와 필드를 대신함
// 상속이 필요한 경우 동반객체는 상속이 불가능하기에 여러개의 보조 생성자를 생성하는 편이 효율적
class User private constructor(val name: String) { //private 주 생성자

    companion object {
        // 이메일(email)을 기준으로 아이디를 분리해서 User 인스턴스 생성
        fun newSubscribingUser(email:String) = User(email.substringBefore("@"))

        // ID를 사용해서 User 인스턴스 생성
        fun newFacebookUser(id:Int) = User("${id}")
    }
}