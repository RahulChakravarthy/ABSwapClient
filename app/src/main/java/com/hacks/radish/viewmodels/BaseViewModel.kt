package com.hacks.radish.viewmodels

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ncapdevi.fragnav.FragNavController

abstract class BaseViewModel : ViewModel() {
    //Short name
    val vms = viewModelScope

    lateinit var startActivityForResultListener : (intent : Intent, requestCode : Int, bundle : Bundle?) -> Unit
    lateinit var fragmentManager : FragNavController
}