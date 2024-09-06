package com.example.prac.domain

class Team2Admin constructor(override val email:String, _floor:Int) :Admin{ //주 생성자

    private var floor:Int = 0
        set(value:Int){
            println("floor $field -> $value")
            field=value
        }

    init{ //초기화 블록. 주 생성자와 함께 사용됨
        this.floor = _floor
    }

    fun moveFloor(floor:Int){this.floor=floor}
}