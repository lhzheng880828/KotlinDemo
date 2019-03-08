package com.android.samples.kotlin.basic.grammar

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-3-7
 */
class MyClassx {

    companion object {
        val myClassField1: Int = 1
        var myClassField2 = "this is myClassField2"
        fun companionFun1() {
            println("this is 1st companion function.")
            foo()
        }
        fun companionFun2() {
            println("this is 2st companion function.")
            companionFun1()
        }
    }
    fun MyClassx.Companion.foo() {
        println("伴随对象的扩展函数（内部）")
    }
    fun test2() {
        MyClassx.foo()
    }
    init {
        test2()
    }
}

val MyClassx.Companion.no: Int
    get() = 10
fun MyClassx.Companion.foo() {
    println("foo 伴随对象外部扩展函数")
}

