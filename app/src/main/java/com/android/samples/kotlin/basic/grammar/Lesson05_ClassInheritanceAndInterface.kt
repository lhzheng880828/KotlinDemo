package com.android.samples.kotlin.basic.grammar

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-2-27
 */
class Lesson05_ClassInheritanceAndInterface {
    //单例写法
    companion object {
        @Volatile private var INSTANCE: Lesson05_ClassInheritanceAndInterface? = null

        fun getInstance(): Lesson05_ClassInheritanceAndInterface =
                INSTANCE ?: synchronized(this){
                    INSTANCE
                            ?: Lesson05_ClassInheritanceAndInterface().also { INSTANCE = it }
                }
    }

    //1. Kotlin 继承

    /*Kotlin 中所有类都继承该 Any 类，它是所有类的超类，对于没有超类型声明的类是默认超类：

    class Example // 从 Any 隐式继承
    Any 默认提供了三个函数：

    equals()

    hashCode()

    toString()
    注意：Any 不是 java.lang.Object。

    如果一个类要被继承，可以使用 open 关键字进行修饰。

    open class Base(p: Int)           // 定义基类

    class Derived(p: Int) : Base(p)
    */

    //2. 构造函数
    //2.1 子类有主构造函数
    //如果子类有主构造函数， 则基类必须在主构造函数中立即初始化。

    open class Person(var name : String, var age : Int){// 基类

    }

    class Student(name : String, age : Int, var no : String, var score : Int) : Person(name, age) {

    }

    // 测试
    fun classInheritanceTest1() {
        val s =  Student("Runoob", 18, "S12346", 89)
        println("学生名： ${s.name}")
        println("年龄： ${s.age}")
        println("学生号： ${s.no}")
        println("成绩： ${s.score}")
    }

    //2.2 子类没有构造函数
    /*子类没有主构造函数
    如果子类没有主构造函数，则必须在每一个二级构造函数中用 super 关键字初始化基类，或者在代理另一个构造函数。初始化基类时，可以调用基类的不同构造方法。

    class Student : Person {

        constructor(ctx: Context) : super(ctx) {
        }

        constructor(ctx: Context, attrs: AttributeSet) : super(ctx,attrs) {
        }
    }

    */

    /**用户基类**/
    open class Person2(name:String){
        /**次级构造函数**/
        constructor(name:String,age:Int):this(name){
            //初始化
            println("-------基类次级构造函数---------")
        }
    }

    /**子类继承 Person 类**/
    class Student2:Person2{

        /**次级构造函数**/
        constructor(name:String,age:Int,no:String,score:Int):super(name,age){
            println("-------继承类次级构造函数---------")
            println("学生名： ${name}")
            println("年龄： ${age}")
            println("学生号： ${no}")
            println("成绩： ${score}")
        }
    }

    fun testNoMainConstrutor() {
        var s =  Student2("Runoob", 18, "S12345", 89)
    }

    //3. 重写
    /*在基类中，使用fun声明函数时，此函数默认为final修饰，不能被子类重写。如果允许子类重写该函数，
    那么就要手动添加 open 修饰它, 子类重写方法使用 override 关键词：

    *//**用户基类**//*
    open class Person{
        open fun study(){       // 允许子类重写
            println("我毕业了")
        }
    }

    *//**子类继承 Person 类**//*
    class Student : Person() {

        override fun study(){    // 重写方法
            println("我在读大学")
        }
    }

    fun main(args: Array<String>) {
        val s =  Student()
        s.study();

    }

    输出结果为:

    我在读大学

    */

    //如果有多个相同的方法（继承或者实现自其他类，如A、B类），
    // 则必须要重写该方法，使用super范型去选择性地调用父类的实现。

    open class A {
        open fun f () { print("A") }
        fun a() { print("a") }
    }

    interface B {
        fun f() { print("B") } //接口的成员变量默认是 open 的
        fun b() { print("b") }
    }

    class C() : A() , B{
        override fun f() {
            //super<A>.f()//调用 A.f()
            //super<B>.f()//调用 B.f()
        }
    }

    fun extendsMultiClass() {
        val c =  C()
        c.f()
    }

    /*C 继承自 a() 或 b(), C 不仅可以从 A 或则 B 中继承函数，而且 C 可以继承 A()、B() 中共有的函数。
    此时该函数在中只有一个实现，为了消除歧义，该函数不必必须调用A()和B()中该函数的实现，并提供自己的实现。

    输出结果为:

    AB

    */


    //4. 属性重写
    /*
    1. 属性重写使用 override 关键字，属性必须具有兼容类型，每一个声明的属性都可以通过初始化程序或者getter方法被重写
    2. 子类继承父类时，不能有跟父类同名的变量，除非父类中该变量为 private，或者父类中该变量为 open 并且子类用 override 关键字重写

    open class Foo {
        open val x: Int get { …… }
    }

    class Bar1 : Foo() {
        override val x: Int = ……
    }
    你可以用一个var属性重写一个val属性，但是反过来不行。因为val属性本身定义了getter方法，
    重写为var属性会在衍生类中额外声明一个setter方法

    你可以在主构造函数中使用 override 关键字作为属性声明的一部分:


    */

    fun testForAttrOverride(){
        var bar1 = Bar1(2)
        var bar2 = Bar2()
    }


    interface Foo {
        val count: Int
    }

    class Bar1(override val count: Int) : Foo

    class Bar2 : Foo {
        override var count: Int = 0
    }

    //5. Kotlin接口

    /*Kotlin 接口与 Java 8 类似，使用 interface 关键字定义接口，允许方法有默认实现：
    */

    interface MyInterface {
        fun bar()    // 未实现
        fun foo() {  //已实现
            // 可选的方法体
            println("foo")
        }
    }

    //5.1 实现接口
    //一个类或者对象可以实现一个或多个接口。



    class Child : MyInterface {
        override fun bar() {
            // 方法体
            println("bar")
        }
    }
    fun mainInterface() {
        val c =  Child()
        c.foo()
        c.bar()

    }

    //5.2 接口中的属性
    //接口中的属性只能是抽象的，不允许初始化值，接口不会保存属性值，实现接口时，必须重写属性：
    interface MyInterface2 {
        var name:String //name 属性, 抽象的
        fun bar()
        fun foo() {
            // 可选的方法体
            println("foo")
        }
    }
    class Child2 : MyInterface2 {
        override var name: String = "runoob" //重写属性
        override fun bar() {
            // 方法体
            println("bar")
        }
    }
    fun mainTestInterfAttr() {
        val c =  Child2()
        c.foo()
        c.bar()
        println(c.name)
    }

    //5.3
    /*函数重写
    实现多个接口时，可能会遇到同一方法继承多个实现的问题。例如:

    实例如下：*/
    interface A1 {
        fun foo() { print("A") }   // 已实现
        fun bar()                  // 未实现，没有方法体，是抽象的
    }

    interface B1 {
        fun foo() { print("B") }   // 已实现
        fun bar() { print("bar") } // 已实现
    }

    class C1 : A1 {
        override fun bar() { print("bar") }   // 重写
    }

    class D : A1, B1 {
        override fun foo() {
            super<A1>.foo()
            super<B1>.foo()
        }

        override fun bar() {
            super<B1>.bar()
        }
    }

    fun mainTestMethodOverride() {
        val d =  D()
        d.foo()
        d.bar()
    }

    /*输出结果为：

    ABbar

    实例中接口 A 和 B 都定义了方法 foo() 和 bar()， 两者都实现了 foo(), B 实现了 bar()。
    因为 C 是一个实现了 A 的具体类，所以必须要重写 bar() 并实现这个抽象方法。
    然而，如果我们从 A 和 B 派生 D，我们需要实现多个接口继承的所有方法，并指明 D 应该如何实现它们。
    这一规则 既适用于继承单个实现（bar()）的方法也适用于继承多个实现（foo()）的方法。
*/
}