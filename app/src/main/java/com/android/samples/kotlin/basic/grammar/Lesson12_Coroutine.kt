package com.android.samples.kotlin.basic.grammar

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-3-14
 */
class Lesson12_Coroutine {

    companion object {
        @Volatile private var INSTANCE: Lesson12_Coroutine? = null

        fun getInstance(): Lesson12_Coroutine =
                INSTANCE ?: synchronized(this){
                    INSTANCE
                            ?: Lesson12_Coroutine().also { INSTANCE = it }
                }
    }

    //1. 第一个协程程序
    fun firstCoroutineProgram(){

        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程已在等待时主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    }


    //2. 桥接阻塞与非阻塞世界
    //本质上，协程是轻量级的线程。 它们在某些 CoroutineScope 上下文中与 launch 协程构建器 一起启动。
    //这里我们在 GlobalScope 中启动了一个新的协程，这意味着新协程的生命周期只受整个应用程序的生命周期限制。

    //可以将 GlobalScope.launch { …… } 替换为 thread { …… }，将 delay(……) 替
    // 换为 Thread.sleep(……) 达到同样目的。但是不能使用thread与delay一起使用， 这是因为 delay 是一个特殊的
    // 挂起函数 ，它不会造成线程阻塞， 但是会挂起协程，并且只能在协程中使用。
    fun testThreadAndSleep(){
        thread {
            Thread.sleep(1000L) // 阻塞等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程已在等待时主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    }


    //显式使用 runBlocking 协程构建器来阻塞
    fun testBlock(){
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L)
            println("World!")
        }
        println("Hello,") // 主线程中的代码会立即执行
        runBlocking {     // 但是这个表达式阻塞了主线程
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
        }
    }

    //使用runBlocking包装主函数
    fun delegateMainFun()= runBlocking<Unit> { // 开始执行主协程
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L)
            println("World!")
        }
        println("Hello,") // 主协程在这里会立即执行
        delay(2000L)      // 延迟 2 秒来保证 JVM 存活
    }

    //3. 等待一个作业完成
    //延迟一段时间来等待另一个协程运行并不是一个好的选择。让我们显式（以非阻塞方式）等待所启动的后台 Job 执行结束：
    fun waitJobFinish()= runBlocking{
        val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        job.join() // 等待直到子协程执行结束
    }

    //4. 结构化并发
    //使用GlobalScope全局创建协程并不好，可以在作用域内创建
    /*
    在我们的示例中，我们使用 runBlocking 协程构建器将 main 函数转换为协程。 包括 runBlocking 在内的每个
    协程构建器都将 CoroutineScope 的实例添加到其代码块所在的作用域中。 我们可以在这个作用域中启动协程而无需显式
    join 之，因为外部协程（示例中的 runBlocking）直到在其作用域中启动的所有协程都执行完毕后才会结束。
    */
    fun StructConcurrent() = runBlocking { // this: CoroutineScope
        launch { // 在 runBlocking 作用域中启动新协程
            delay(1000L)
            println("World!")
        }
        println("Hello,")
    }

    //5. 作用域构建器
    /*
    除了由不同的构建器提供协程作用域之外，还可以使用 coroutineScope 构建器声明自己的作用域。它会创建新的协程作用域并且在
    所有已启动子协程执行完毕之前不会结束。runBlocking 与 coroutineScope 的主要区别在于后者在等待所有子协程执行完毕时不会阻塞当前线程。
    */

    fun testScopeBuilder() = runBlocking { // this: CoroutineScope
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope { // 创建一个新的协程作用域
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }

        println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
    }

    //6. 提取函数重构
    fun suspendFun() = runBlocking{
        launch { doWorld() }
        println("Hello,")
    }

    // 这是你的第一个挂起函数
    suspend fun doWorld() {
        delay(1000L)
        println("World!")
    }

    //7. 协程很轻量
    fun testLightCoroutine() = runBlocking {
        repeat(100_000) { // 启动大量的协程
            launch {
                delay(1000L)
                print(".")
            }
        }
    }

    //8.全局协程像守护线程
    //在 GlobalScope 中启动的活动协程并不会使进程保活。它们就像守护线程。
    fun testGlobalCoroutine() = runBlocking {
        //sampleStart
        GlobalScope.launch {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // 在延迟后退出
        //sampleEnd
    }

}