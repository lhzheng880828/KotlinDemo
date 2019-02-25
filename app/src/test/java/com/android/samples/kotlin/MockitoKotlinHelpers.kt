package com.android.samples.kotlin

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

    @Before
    fun setUp() {
        print("before test ")

    }

    @Test
    fun testVars() {

    }

    @After
    fun afterTest(){
        print("after test ")
    }


}