package com.android.currencyconverterv2.presentation.main_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.currencyconverterv2.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



}