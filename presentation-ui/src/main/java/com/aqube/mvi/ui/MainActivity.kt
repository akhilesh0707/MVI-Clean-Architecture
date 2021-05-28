package com.aqube.mvi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aqube.mvi.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}