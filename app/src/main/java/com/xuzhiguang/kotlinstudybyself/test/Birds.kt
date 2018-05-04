package com.xuzhiguang.kotlinstudybyself.test

/**
 * Created by Administrator on 2018/4/27.
 */
class Birds(val whic: String) : Base {
    override fun fly() {
        print(whic + "can fly")
    }

}

class MaQue(b: Base) : Base by b{
    init {
        whoFly()
    }

    fun whoFly() {
        val bird = Birds("麻雀")
        MaQue(bird).fly()
    }
}
