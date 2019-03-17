package com.hacks.radish.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    //lateinit var fragmentComponent: FragmentComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //fragmentComponent = DaggerFragmentComponent.builder().fragmentModule(FragmentModule(supportFragmentManager)).build()
    }

}