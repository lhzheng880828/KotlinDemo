package com.android.samples.kotlin.basic

import android.content.Context

//包声明, 可以为任意

//import java.util.* 包带入，结尾不带;

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-2-25
 */
class LanguagePractice {


    fun main(args: Array<String>) {    // 包级可见的函数，接受一个字符串数组作为参数
        println("Hello World!") // 分号可以省略
        Greeter("World!").greet()
    }

    //面向对象编程
    class Greeter(val name: String) {
        fun greet() {
            println("Hello, $name")
        }
    }

    //函数定义
    //1. 函数定义使用关键字 fun，参数格式为：参数 : 类型
    fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
        return a + b
    }
    //2. 表达式作为函数体，返回类型自动推断：
    fun sum1(a: Int, b: Int) = a + b

    //3. public 方法则必须明确写出返回类型
    public fun sum2(a: Int, b: Int): Int = a + b

    //4. 无返回值的函数(类似Java中的void)：
    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }

    //5. 如果是返回 Unit类型，则可以省略(对于public方法也是这样)：
    public fun printSum1(a: Int, b: Int) {
        print(a + b)
    }

    //6. 可变长参数函数
    //函数的变长参数可以用 vararg 关键字进行标识：
    fun vars(vararg v:Int){
        for(vt in v){
            print(vt)
        }
    }
    // 测试
    fun varsTest(args: Array<String>) {
        vars(1,2,3,4,5)  // 输出12345
    }

    //7. lambda(匿名函数)
    //lambda表达式使用实例：

    // 测试
    fun sumLambdaTest() {
        val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
        println(sumLambda(1,2))  // 输出 3
    }

    companion object {
        @Volatile private var INSTANCE: LanguagePractice? = null

        fun getInstance(context: Context): LanguagePractice =
                INSTANCE?: synchronized(this){
                    INSTANCE?:LanguagePractice().also { INSTANCE = it }
                }
    }

}