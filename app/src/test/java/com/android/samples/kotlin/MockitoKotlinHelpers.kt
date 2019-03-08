package com.android.samples.kotlin

import com.android.samples.kotlin.basic.grammar.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-2-25
 */
class MockitoKotlinHelpers {
    /**
     * Returns ArgumentCaptor.capture() as nullable type to avoid java.lang.IllegalStateException
     * when null is returned.
     */
    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

    var langPrac: Lesson01_BasicLangGrammar = Lesson01_BasicLangGrammar.getInstance()

    var dataType: Lesson02_BasicDataType = Lesson02_BasicDataType.getInstance()

    var conditionAndLoop: Lesson03_ConditionAndLoop = Lesson03_ConditionAndLoop.getInstance()

    var classAndObj: Lesson04_ClassAndObject = Lesson04_ClassAndObject.getInstance()

    var classInheritanceAndInterface: Lesson05_ClassInheritanceAndInterface = Lesson05_ClassInheritanceAndInterface.getInstance()

    var kotlinExtend: Lesson06_KotlinExtend = Lesson06_KotlinExtend.getInstance()

    var dataClass: Lesson07_DataClassAndSealedCLass = Lesson07_DataClassAndSealedCLass.getInstance()

    var genericType: Lesson08_GenericType = Lesson08_GenericType.getInstance()

    var enumClass: Lesson09_EnumClass = Lesson09_EnumClass.getInstance()

    @Before
    fun setUp() {
        print("before test\n")
    }

    //1. 基本语法测试
    @Test
    fun testMain(){
        //[1,2,3]
        val a = arrayOf(1, 2, 3)
        for (item in a) print("a item = $item \n")
        //[0,2,4]
        val b = Array(3, { i -> (i * 2) })
        for (i in b.indices) {
            print("b array item = "+(b[i])+"\n")
        }

        val str = arrayOf("123", "456", "789")
        langPrac.main(str)
    }

    //2. 函数定义测试
    @Test
    fun testVars() {

        langPrac.vars(1,2,4,6)
        print("\n")
    }

    @Test
    fun testSum(){
        langPrac.sumLambdaTest(3, 6)
    }

    //5.常量与变量
    @Test
    fun testConstAndVar(){
        langPrac.constAndVar()
    }

    //6. 字符串模板
    @Test
    fun testStringTemplate(){
        langPrac.stringTemplate()
    }

    //7. NULL检查机制
    @Test
    fun testNull(){
        //langPrac.testNull(null) 抛出NullPointerException
        //langPrac.testNull("abc") 抛出 NumberFormatException
        langPrac.testNull("123")
    }

    @Test
    fun testReturnNull(){
        val intArray = arrayOf("12", "34")
        langPrac.returnNull(intArray)//正常

        val stringArray = arrayOf("12ab", "34ab")
        langPrac.returnNull(stringArray)//抛出NumberFormatException

        //langPrac.returnNull(arrayOf(null, "34ab"))//正常

    }

    //8. 类型检测及自动类型转换
    @Test
    fun getStringLength(){
        langPrac.getStringLength("abc")
        langPrac.getStringLength1(10)
        langPrac.getStringLength2('a')

    }

    //9.区间测试
    @Test
    fun rangeTest(){
        langPrac.rangeSample()
    }

    //10. 数字比较
    @Test
    fun numCompareTest(){
        dataType.numCompare()
    }

    //11. 类型转换
    @Test
    fun typeSwitchTest(){
        dataType.typeSwitch()
    }

    //12. 位操作
    @Test
    fun bitsOperateTest(){
        dataType.bitsOperate()
    }

    //13. 字符
    @Test
    fun decimalDigitValueTest(){
        dataType.decimalDigitValue('a')
    }

    //14. 逻辑操作
    @Test
    fun boolOpTest(){
        dataType.boolOp()
    }

    //15.数组操作
    @Test
    fun arrayOpTest(){
        dataType.arrayOp()
    }

    //16.字符串操作
    @Test
    fun stringOpSampleTest(){
        dataType.stringSample()
    }

    //17. 字符串模板
    @Test
    fun stringTempalete(){
        dataType.stringTemplateSample()
    }

    //18. if条件判断
    @Test
    fun conditionTest(){
        conditionAndLoop.ifCondition(3,2)
        conditionAndLoop.whenCondition()
    }

    //19. for循环
    @Test
    fun forLoopTest(){
        conditionAndLoop.sampleForLoop()
    }

    //20. while && do...while循环
    @Test
    fun whileLoopTest(){
        conditionAndLoop.sampleForWhile()
    }

    //21. Break 和Continue
    @Test
    fun returnAndBreakTest(){
        conditionAndLoop.returnAndBreak()
    }

    //22. Label 返回
    @Test
    fun labelSampleTest(){
        conditionAndLoop.labelSample()
        conditionAndLoop.labelReturnSample()
    }

    //23. 构造函数
    @Test
    fun constructorTest(){
        classAndObj.testConstructor()
        classAndObj.testConstructor2()
    }

    //24. 匿名内部类
    @Test
    fun anonymousClassTest(){
        classAndObj.anonymousClass()
    }

    //25. 继承子类有主构造函数
    @Test
    fun classInheriTest(){
        classInheritanceAndInterface.classInheritanceTest1()
        classInheritanceAndInterface.testNoMainConstrutor()
    }

    //26. 继承多个类的同一个方法
    @Test
    fun testMultiFun(){
        classInheritanceAndInterface.extendsMultiClass()
    }

    //27. 复写类属性
    @Test
    fun testFiledOverride(){
        classInheritanceAndInterface.testForAttrOverride()
    }

    //28.接口测试
    @Test
    fun testInterface(){
        classInheritanceAndInterface.mainInterface()
    }

    //29. 接口属性测试

    @Test
    fun testInterfaceAttr(){
        classInheritanceAndInterface.mainTestInterfAttr()
    }

    //30. 接口函数重写
    @Test
    fun testMethodOverride(){
        classInheritanceAndInterface.mainTestMethodOverride()
    }

    //31. Kotlin扩展函数
    @Test
    fun testKotlinExtend(){
        //user类添加扩展函数
        kotlinExtend.userExtend()
        //为MutableList添加扩展函数
        kotlinExtend.mutableListExtend()
        //扩展函数是静态解析的
        kotlinExtend.staticParse()
        //扩展函数和成员函数同名，则有限调用成员函数
        kotlinExtend.extendFunSameWithMemFun()
        //扩展一个空对象
        kotlinExtend.extendNullObj()
        //扩展属性
        kotlinExtend.extendProperty()
        //扩展伴生对象
        kotlinExtend.companionObjExtend()

        //扩展声明为成员
        kotlinExtend.extendDeclareMem()

        //扩展声明为成员，对分发接受者是虚拟的
        kotlinExtend.testExtendOpen()

        //
    }

    //32. 伴随类的补充
    @Test
    fun testKotlinSupple(){
        kotlinExtend.testClassComp()
    }

    //33.数据类实现
    @Test
    fun testDataClass(){
        dataClass.testDataClass()
    }



    @After
    fun afterTest(){
        print("after test ")
    }


}