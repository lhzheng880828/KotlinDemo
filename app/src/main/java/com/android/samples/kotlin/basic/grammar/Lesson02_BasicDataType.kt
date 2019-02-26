package com.android.samples.kotlin.basic.grammar

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-2-26
 */
class Lesson02_BasicDataType {
    //1. 数据类型及位宽度及字面常量
    /*Double	64
    Float	32
    Long	64
    Int	32
    Short	16
    Byte	8*/

    fun constForDataType(){
        val constInt = 123
        val constHex = 0xff
        val constStr = "abc"
        val constLongInt = 123L
        val constBinary = 0b00001011
        //浮点数支持
        val constDouble = 123.5
        val constDouble1 = 123.5e10
        val constFoat = 123.5f
        //你可以使用下划线使数字常量更易读：
        val oneMillion = 1_000_000
        val creditCardNumber = 1234_5678_9012_3456L
        val socialSecurityNumber = 999_99_9999L
        val hexBytes = 0xFF_EC_DE_5E
        val bytes = 0b11010010_01101001_10010100_10010010
    }

    //2. 比较两个数
    /*Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，其实 Kotlin 帮你封装了一个对象，这样可以保证不会出现空指针。数字类型也一样，所有在比较两个数字的时候，就有比较数据大小和比较两个对象是否相同的区别了。

    **********
    在 Kotlin 中，三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小。*/
    fun numCompare(){
        val a: Int = 10000
        println(a === a) // true，值相等，对象地址相等

        print("=====================\n")
        //经过了装箱，创建了两个不同的对象
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a

        //虽然经过了装箱，但是值是相等的，都是10000
        println(boxedA === anotherBoxedA) //  false，值相等，对象地址不一样
        print("=====================\n")
        println(boxedA == anotherBoxedA) // true，值相等
        print("=====================\n")
    }

    //3. 类型转换
    //由于不同的表示方式，较小类型并不是较大类型的子类型，较小的类型不能隐式转换为较大的类型。
    // 这意味着在不进行显式转换的情况下我们不能把 Byte 型值赋给一个 Int 变量。

    //每种数据类型都有下面的这些方法，可以转化为其它的类型：
    /*toByte(): Byte
    toShort(): Short
    toInt(): Int
    toLong(): Long
    toFloat(): Float
    toDouble(): Double
    toChar(): Char*/

    fun typeSwitch(){
        val b: Byte = 1 // OK, 字面值是静态检测的
        //val i: Int = b // 错误
        print("b = $b \n")
        //自动类型转化的，前提是可以根据上下文环境推断出正确的数据类型而且数学操作符会做相应的重载。
        val l = 1L + 3 // Long + Int => Long

        print("l = $l \n")
    }

    //4. 位操作
    //对于Int和Long类型，还有一系列的位操作符可以使用，分别是：
    /*shl(bits) – 左移位 (Java’s <<)
    shr(bits) – 右移位 (Java’s >>)
    ushr(bits) – 无符号右移位 (Java’s >>>)
    and(bits) – 与
    or(bits) – 或
    xor(bits) – 异或
    inv() – 反向*/
    fun bitsOperate(){
        val shLeft = 1.shl(1)
        print("shLeft = $shLeft \n")
        val shRight = 1.shr(1)
        print("shRight = $shRight \n")
        val shUnRight = 100.ushr(2)
        print("shUnRight = $shUnRight \n")
        val andVal = 10.and(0)
        print("andVal = $andVal \n")
        val orVal = 2.or(1)
        print("orVal = $orVal \n")
        val invVal = 1.inv()
        print("invVal = $invVal \n")

    }

    //5. 字符
    //(1)Kotlin 中的 Char 不能直接和数字操作，Char 必需是单引号 ' 包含起来的。比如普通字符 '0'，'a'。
    //(2)特殊字符可以用反斜杠转义。 支持这几个转义序列：\t、 \b、\n、\r、\'、\"、\\ 和 \$。
    // 编码其他字符要用 Unicode 转义序列语法：'\uFF00'。
    fun decimalDigitValue(c: Char): Int {
        /*if (c == 1) { // 错误：类型不兼容
            ……
        }*/
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() // 显式转换为数字
    }

    //6. 布尔操作
    /*布尔用 Boolean 类型表示，它有两个值：true 和 false。

    若需要可空引用布尔会被装箱。

    内置的布尔运算有：

    || – 短路逻辑或
    && – 短路逻辑与
    ! - 逻辑非*/
    fun boolOp(){
        val b1 = true
        val b2 = false
        val b3 = b1 || b2
        val b4 = b3 && b2
        var b5 = !b4
    }

    //7. 数组操作
    /*数组用类 Array 实现，并且还有一个 size 属性及 get 和 set 方法，由于使用 [] 重载了 get 和 set 方法，
    所以我们可以通过下标很方便的获取或者设置数组对应位置的值。
    数组的创建两种方式：一种是使用函数arrayOf()；另外一种是使用工厂函数。如下所示，我们分别是两种方式创建了两个数组：*/
    fun arrayOp(){
        //[1,2,3]
        val a = arrayOf(1, 2, 3)
        //[0,2,4]
        val b = Array(3, { i -> (i * 2) })

        //读取数组内容
        println(a[0])    // 输出结果：1
        print("=====================\n")
        println(b[1])    // 输出结果：2
        print("=====================\n")

        val x: IntArray = intArrayOf(1, 2, 3)
        x[0] = x[1] + x[2]
    }

    /*如上所述，[] 运算符代表调用成员函数 get() 和 set()。

    注意: 与 Java 不同的是，Kotlin 中数组是不型变的（invariant）。

    除了类Array，还有ByteArray, ShortArray, IntArray，用来表示各个类型的数组，省去了装箱操作，因此效率更高，其用法同Array一样,
    如上例最后一行*/

    //8. 字符串

    fun stringSample(){
        //1. 遍历字符串中的字符
        val str = "abcd"
        for (c in str) {
            println(c)
        }
        print("-------------------------------\n")
        //2. 多行字符串
        val text = """
        多行字符串
        多行字符串
        """
        println(text)   // 输出有一些前置空格
        print("-------------------------------\n")

        //3. 通过 trimMargin() 方法来删除多余的空白
        //默认 | 用作边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">")
        val text1 = """
        |多行字符串
        |菜鸟教程
        |多行字符串
        |Runoob
        """.trimMargin()
        println(text1)    // 前置空格删除了
    }

    //9. 字符串模板
    //字符串模板以$开头
    fun stringTemplateSample(){
        val i = 10
        val s = "i = $i" // 求值结果为 "i = 10"
        println(s)
        print("-------------------------------\n")
        //花括号扩起来的任意表达式
        val s1 = "runoob"
        val str = "$s1.length is ${s1.length}" // 求值结果为 "runoob.length is 6"
        print("-------------------------------\n")
        //原生字符串和转义字符串内部都支持模板。 如果你需要在原生字符串中表示字面值 $ 字符（它不支持反斜杠转义）
        val price = """
        ${'$'}9.99
        """
        println(price)
        println(str)
    }



    //单例写法
    companion object {
        @Volatile private var INSTANCE: Lesson02_BasicDataType? = null

        fun getInstance(): Lesson02_BasicDataType =
                INSTANCE ?: synchronized(this){
                    INSTANCE
                            ?: Lesson02_BasicDataType().also { INSTANCE = it }
                }
    }
}