package com.android.samples.kotlin.basic.weather

import android.util.Log
import java.net.URL

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-3-11
 */
class Request(val url: String) {

    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}