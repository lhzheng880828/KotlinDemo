package com.android.samples.kotlin.basic.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.samples.kotlin.R
import kotlinx.android.synthetic.main.test_act_layout.*


/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-3-11
 */
class TestMainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.test_act_layout)
        message.text = "testMessage"


        /*async() {
            Request(url).run()
            uiThread { longToast("Request performed") }
        }*/


        val forecastList = findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)

    }



    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )




    companion object {
        fun startAct(context: Context){
            val intent  = Intent()
            intent.setClass(context, TestMainActivity::class.java)
            context.startActivity(intent)
        }
    }


}