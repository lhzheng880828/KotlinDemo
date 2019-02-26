package com.android.samples.kotlin.basic.grammar

//包声明, 可以为任意

//import java.util.* 包带入，结尾不带;

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-2-25
 */
class Lesson01_BasicLangGrammar {

    //1. 基本语法
    fun main(args: Array<String>) {    // 包级可见的函数，接受一个字符串数组作为参数
        println("Hello World!") // 分号可以省略
        for(item : String in args){
            print("main item = $item \n")

        }

        Greeter("World!").greet()
    }

    //2. 面向对象编程
    class Greeter(val name: String) {
        fun greet() {
            println("Hello, $name \n")
        }
    }

    //3. 函数定义
    //3.1 函数定义使用关键字 fun，参数格式为：参数 : 类型
    fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
        return a + b
    }
    //3.2  表达式作为函数体，返回类型自动推断：
    fun sum1(a: Int, b: Int) = a + b

    //3.3 public 方法则必须明确写出返回类型
    public fun sum2(a: Int, b: Int): Int = a + b

    //3.4  无返回值的函数(类似Java中的void)：
    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }

    //3.5 如果是返回 Unit类型，则可以省略(对于public方法也是这样)：
    public fun printSum1(a: Int, b: Int) {
        print(a + b)
    }

    //3.6 可变长参数函数
    //函数的变长参数可以用 vararg 关键字进行标识：
    fun vars(vararg v:Int){
        for(vt in v){
            print(vt)
        }
    }

    //3.7 lambda(匿名函数)
    //lambda表达式使用实例：

    fun sumLambdaTest(a: Int, b: Int) {
        val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
        println(sumLambda(a,b))  // 输出 3
    }

    //单例写法
    companion object {
        @Volatile private var INSTANCE: Lesson01_BasicLangGrammar? = null

        fun getInstance(): Lesson01_BasicLangGrammar =
                INSTANCE ?: synchronized(this){
                    INSTANCE
                            ?: Lesson01_BasicLangGrammar().also { INSTANCE = it }
                }
    }

    //4. 常量与变量
    /*可变变量定义：var 关键字

    var <标识符> : <类型> = <初始化值>
    不可变变量定义：val 关键字，只能赋值一次的变量(类似Java中final修饰的变量)

    val <标识符> : <类型> = <初始化值>
    常量与变量都可以没有初始化值,但是在引用前必须初始化

    编译器支持自动类型判断,即声明时可以不指定类型,由编译器判断。*/

    fun constAndVar(){
        val a: Int = 1
        val b = 1       // 系统自动推断变量类型为Int
        val c: Int      // 如果不在声明时初始化则必须提供变量类型
        c = 1           // 明确赋值


        var x = 5        // 系统自动推断变量类型为Int
        x += 1           // 变量可修改

        print("[constAndVar]a = $a, b = $b, c= $c, x = $x \n")
    }

    // 5. 注释
    //Kotlin 支持单行和多行注释，实例如下：

    // 这是一个单行注释

    /* 这是一个多行的
       块注释。 */
    //与 Java 不同, Kotlin 中的块注释允许嵌套。

    //6. 字符串模板
    /*$ 表示一个变量名或者变量值

    $varName 表示变量值

    ${varName.fun()} 表示变量的方法返回值:*/
    fun stringTemplate(){
        var a = 1
        // 模板中的简单名称：
        val s1 = "a is $a"
        print(s1)
        a = 2
        // 模板中的任意表达式：
        val s2 = "${s1.replace("is", "was")}, but now is $a"
        print(s2)
    }


    //7. NULL检查机制
    //Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式，字段后加!!像Java一样抛出空异常，另一种字段后加?可不做处理返回值为 null或配合?:做空判断处理

    //类型后面加?表示可为空
    var age: String? = "23"
    //抛出异常
    val ages = age!!.toInt()
    //不做处理返回 null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt() ?: -1
    /*当一个引用可能为 null 值时, 对应的类型声明必须明确地标记为可为 null。

    当 str 中的字符串内容不是一个整数时, 返回 null:*/

    private fun parseInt(str: String?): Int? {
        // ...
        return str?.toInt()
    }

    fun  testNull(age: String?){
        //抛出空指针异常
        val ages = age!!.toInt()
        //不做处理返回 null
        val ages1 = age?.toInt()
        //age为空返回-1
        val ages2 = age?.toInt() ?: -1

        print("ages = $ages, ages1 = $ages1, ages2 = $ages2 \n")
    }

    fun returnNull(args: Array<String>){
        if (args.size < 2) {
            print("Two integers expected")
            return
        }
        val x = parseInt(args[0])
        val y = parseInt(args[1])
        print("x == $x, y == $y \n")
        // 直接使用 `x * y` 会导致错误, 因为它们可能为 null.
        if (x != null && y != null) {
            // 在进行过 null 值检查之后, x 和 y 的类型会被自动转换为非 null 变量
            print(x * y)
        }
    }

    // 8. 类型检测及自动类型转换
    //我们可以使用 is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字)。

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // 做过类型判断以后，obj会被系统自动转换为String类型
            return obj.length
        }

        //在这里还有一种方法，与Java中instanceof不同，使用!is
        // if (obj !is String){
        //   // XXX
        // }

        // 这里的obj仍然是Any类型的引用
        return null
    }

    //或者
    fun getStringLength1(obj: Any): Int? {
        if (obj !is String)
            return null
        // 在这个分支中, `obj` 的类型会被自动转换为 `String`
        return obj.length
    }

    //或者
    fun getStringLength2(obj: Any): Int? {
        // 在 `&&` 运算符的右侧, `obj` 的类型会被自动转换为 `String`
        if (obj is String && obj.length > 0)
            return obj.length
        return null
    }


    //9. 区间
    /*区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。

    区间是为任何可比较类型定义的，但对于整型原生类型，它有一个优化的实现。以下是使用区间的一些示例:*/
    fun rangeSample(){
        for (i in 1..4) print(i) // 输出“1234”
        println("\n----------------")
        for (i in 4..1) print(i) // 什么都不输出

        println("\n----------------")

// 使用 step 指定步长
        for (i in 1..4 step 2) print(i) // 输出“13”
        println("\n----------------")
        for (i in 4 downTo 1 step 2) print(i) // 输出“42”
        println("\n----------------")

// 使用 until 函数排除结束元素
        for (i in 1 until 10) {   // i in [1, 10) 排除了 10
            println(i)
            if (i in 1..10) { // 等同于 1 <= i && i <= 10
                println(i)
            }
        }
    }

}