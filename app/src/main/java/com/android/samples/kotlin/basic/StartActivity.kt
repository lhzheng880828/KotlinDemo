package com.android.samples.kotlin.basic


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.android.samples.kotlin.R

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
}
