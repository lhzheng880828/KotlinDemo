package com.android.samples.kotlin.basic.grammar

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-2-26
 */
class Lesson03_ConditionAndLoop() {

    //单例写法
    companion object {
        @Volatile private var INSTANCE: Lesson03_ConditionAndLoop? = null

        fun getInstance(): Lesson03_ConditionAndLoop =
                INSTANCE ?: synchronized(this){
                    INSTANCE
                            ?: Lesson03_ConditionAndLoop().also { INSTANCE = it }
                }
    }

    //1. if语句
    fun ifCondition(a: Int, b:Int){

        // （1）.传统用法
        var max = a
        if (a < b) max = b
        print("max = $max \n")
        // （2）. 使用 else
        var max1: Int
        if (a > b) {
            max1 = a
        } else {
            max1 = b
        }
        print("max1 = $max1 \n")

        // （3）. 作为表达式, 推荐简化写法
        val max2 = if (a > b) a else b

        print("max2 = $max2 \n")

        val max3 = if (a > b) {
            print("Choose a")
            a
        } else {
            print("Choose b")
            b
        }
        print("max3 = $max3 \n")


        //（4）. 多分支写法
        var x = 0
        if(x>0){
            println("x 大于 0")
        }else if(x==0){
            println("x 等于 0")
        }else{
            println("x 小于 0")
        }

       //（5）. 使用 in 运算符来检测某个数字是否在指定区间内，区间格式为 x..y
        val x1 = 5
        val y1 = 9
        if (x1 in 1..8) {
            println("x 在区间内")
        }
    }

    //2. when表达式
    /*when 将它的参数和所有的分支条件顺序比较，直到某个分支满足条件。

    when 既可以被当做表达式使用也可以被当做语句使用。如果它被当做表达式，符合条件的分支的值就是整个表达式的值，如果当做语句使用， 则忽略个别分支的值。

    when 类似其他语言的 switch 操作符。其最简单的形式如下：*/
    fun whenCondition(){
        //(1). 一般写法
        val x = 2
        when (x) {
            1 -> print("x == 1 \n")
            2 -> print("x == 2 \n")
            else -> { // 注意这个块
                print("x 不是 1 ，也不是 2 \n")
            }
        }

        /*(2). case合并写法
        在 when 中，else 同 switch 的 default。如果其他分支都不满足条件将会求值 else 分支。

        如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔：*/
        when (x) {
            0, 1 -> print("x == 0 or x == 1 \n")
            else -> print("otherwise \n")
        }

        //(3). 也可以检测一个值在（in）或者不在（!in）一个区间或者集合中
        when (x) {
            in 1..10 -> print("x is in the range \n")
            in 11..21 -> print("x is valid \n")
            !in 10..20 -> print("x is outside the range \n")
            else -> print("none of the above \n")
        }

        //(4). 另一种可能性是检测一个值是（is）或者不是（!is）一个特定类型的值。
        // 注意： 由于智能转换，你可以访问该类型的方法和属性而无需 任何额外的检测。

        var hasPref = hasPrefix(x)
        print("hasPref = $hasPref \n")

        //(5). when 也可以用来取代 if-else if链。
        // 如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支
        val items = setOf("apple", "banana", "kiwi")
        when {
            "orange" in items -> println("juicy \n")
            "apple" in items -> println("apple is fine too \n")
        }

    }

    private fun hasPrefix(x: Any) = when(x) {
        is String -> x.startsWith("prefix")
        else -> false
    }


    //3. 循环控制
    /*for 循环可以对任何提供迭代器（iterator）的对象进行遍历，语法如下:

    for (item in collection) print(item)

    循环体可以是一个代码块:

    for (item: Int in ints) {
        // ……
    }

    如上所述，for 可以循环遍历任何提供了迭代器的对象。

    如果你想要通过索引遍历一个数组或者一个 list，你可以这么做：

    for (i in array.indices) {
        print(array[i])
    }

    注意这种"在区间上遍历"会编译成优化的实现而不会创建额外对象。
    或者你可以用库函数 withIndex：

    for ((index, value) in array.withIndex()) {
        println("the element at $index is $value")
    }

    */

    fun sampleForLoop(){
        val items = listOf("apple", "banana", "kiwi")
        for (item in items) {
            println(item)
        }

        print("-------------------\n")

        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }
    }

    //4. while与do..while循环

    /*while是最基本的循环，它的结构为：

        while( 布尔表达式 ) {
            //循环内容
        }

    do…while 循环 对于 while 语句而言，如果不满足条件，则不能进入循环。但有时候我们需要即使不满足条件，也至少执行一次。

    do…while 循环和 while 循环相似，不同的是，do…while 循环至少会执行一次。

        do {
            //代码语句
        }while(布尔表达式);

    */

    fun sampleForWhile(){
        println("----while 使用-----")
        var x = 5
        while (x > 0) {
            println( x--)
        }
        println("----do...while 使用-----")
        var y = 5
        do {
            println(y--)
        } while(y>0)
    }

    //5. 返回与跳转

    /*
    Kotlin 有三种结构化跳转表达式：

    return。默认从最直接包围它的函数或者匿名函数返回。
    break。终止最直接包围它的循环。
    continue。继续下一次最直接包围它的循环。
    在循环中 Kotlin 支持传统的 break 和 continue 操作符。
    */
    fun returnAndBreak(){
        for (i in 1..10) {
            if (i==3) continue  // i 为 3 时跳过当前循环，继续下一次循环
            println(i)
            if (i>5) break   // i 为 6 时 跳出循环
        }
    }

    //6. Break 和 Continue 标签
    /*在 Kotlin 中任何表达式都可以用标签（label）来标记。 标签的格式为标识符后跟 @ 符号，
    例如：abc@、fooBar@都是有效的标签。 要为一个表达式加标签，我们只要在其前加标签即可。

        loop@ for (i in 1..100) {
            // ……
        }

    */

    fun labelSample(){
        //标签限制的 break 跳转到刚好位于该标签指定的循环后面的执行点。
        // continue 继续标签指定的循环的下一次迭代。
        loop@ for (i in 1..25) {
            for (j in 1..21) {
                if (j>20) break@loop
                print("i= $i, j= $j")
                if(j == 10)continue
            }
        }
    }

    //7. 标签处返回
    /*
    (1) 通常我们return是从包围它的函数返回，如下是直接返回到loop

    fun foo() {
        ints.forEach lit@ {
            if (it == 0) return@lit
            print(it)
        }
    }

    (2) 从lambda表达式返回
        fun foo() {
        ints.forEach lit@ {
            if (it == 0) return@lit
            print(it)
        }
    }
    (3) 现在，它只会从 lambda 表达式中返回。通常情况下使用隐式标签更方便。 该标签与接受该 lambda 的函数同名。

        fun foo() {
        ints.forEach {
            if (it == 0) return@forEach
            print(it)
        }
    }
    (4)或者，我们用一个匿名函数替代 lambda 表达式。 匿名函数内部的 return 语句将从该匿名函数自身返回
        fun foo() {
        ints.forEach(fun(value: Int) {
            if (value == 0) return
            print(value)
        })
    }
    当要返一个回值的时候，解析器优先选用标签限制的 return，即

    return@a 1

    意为"从标签 @a 返回 1"，而不是"返回一个标签标注的表达式 (@a 1)"。
    */

    fun labelReturnSample(){
        val intArray = arrayListOf(1,2,3,0,8)
        intArray.forEach {
            if (it == 0) return@forEach
            print("it = $it \n")
        }

        print("label sample test ")
    }

}