package com.android.samples.kotlin.basic.grammar

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-2-26
 */
class Lesson04_ClassAndObject {
    //单例写法
    companion object {
        @Volatile private var INSTANCE: Lesson04_ClassAndObject? = null

        fun getInstance(): Lesson04_ClassAndObject =
                INSTANCE ?: synchronized(this){
                    INSTANCE
                            ?: Lesson04_ClassAndObject().also { INSTANCE = it }
                }
    }

    //1. 定义一个空类
    class Empty

    //2.定义一个类
    class Runoob constructor(name: String) {
        //(1) 定义属性
        var name: String = name
        var url: String = "http://www.runoob.com"
        var city: String = "CN"
        var siteName = name
        //(2) 定义方法
        fun foo() { print("Foo") } // 成员函数

        init {
            println("初始化网站名: ${name}")
        }

        fun printTest() {
            println("我是类的函数")
        }
    }

    //3. 类实例化
    val site = Runoob("ZhangSan") // Kotlin 中没有 new 关键字

    //4.属性引用
    val nameStr = site.name          // 使用 . 号来引用
    var urlStr = site.url

    //5. Koltin 中的类可以有一个 主构造器，以及一个或多个次构造器，
    /*主构造器是类头部的一部分，位于类名称之后, 如类@link{Runoob}

    class Person constructor(firstName: String) {}

    如果主构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略。

    class Person(firstName: String) {

    }

    */

    //6. getter 和 setter

    /*属性声明的完整语法：

    var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]

    getter 和 setter 都是可选

    如果属性类型可以从初始化语句或者类的成员函数中推断出来，那就可以省去类型，val不允许设置setter函数，因为它是只读的。

    var allByDefault: Int? // 错误: 需要一个初始化语句, 默认实现了 getter 和 setter 方法
    var initialized = 1    // 类型为 Int, 默认实现了 getter 和 setter
    val simple: Int?       // 类型为 Int ，默认实现 getter ，但必须在构造函数中初始化
    val inferredType = 1   // 类型为 Int 类型,默认实现 getter

    */

    fun testPerson(){
        var person: Person = Person()

        person.lastName = "wang"

        println("lastName:${person.lastName}")

        person.no = 9
        println("no:${person.no}")

        person.no = 20
        println("no:${person.no}")
    }

    //7. lateinit 延迟初始化
    //非空属性必须在定义的时候初始化,kotlin提供了一种可以延迟初始化的方案,使用 lateinit 关键字描述
    /*

        lateinit var subject: Person

        @SetUp fun setup() {
            subject = Person()
        }

        @Test fun test() {
            subject.method()  // dereference directly
        }
    */

    //8. 主构造器

    /*主构造器中不能包含任何代码，初始化代码可以放在初始化代码段中，初始化代码段使用 init 关键字作为前缀。

    class Person constructor(firstName: String) {
        init {
            println("FirstName is $firstName")
        }
    }

    主构造器的参数可以在初始化代码段中使用，也可以在类主体n定义的属性初始化代码中使用。
    一种简洁语法，可以通过主构造器来定义属性并初始化属性值（可以是var或val）：

    class People(val firstName: String, val lastName: String) {
        //...
    }

    如果构造器有注解，或者有可见度修饰符，这时constructor关键字是必须的，注解和修饰符要放在它之前。

    */
    fun testConstructor(){
        val runoob =  Runoob("菜鸟教程")
        println(runoob.siteName)
        println(runoob.url)
        println(runoob.city)
        runoob.printTest()
    }



    class Person {

        var lastName: String = "zhang"
            get() = field.toUpperCase()   // 将变量赋值后转换为大写
            set

        //Kotlin 中类不能有字段。提供了 Backing Fields(后端变量) 机制,
        // 备用字段使用field关键字声明,field 关键词只能用于属性的访问器
        var no: Int = 100
            get() = field                // 后端变量
            set(value) {
                if (value < 10) {       // 如果传入的值小于 10 返回该值
                    field = value
                } else {
                    field = -1         // 如果传入的值大于等于 10 返回 -1
                }
            }

        var heiht: Float = 145.4f
            private set
    }

}