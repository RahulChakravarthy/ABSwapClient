package com.hacks.radish.viewmodels

import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.managers.ApplicationFragmentManager

abstract class BaseViewModel : ViewModel() {
    //Short name
    val vms = viewModelScope

    lateinit var startActivityForResultListener : (intent : Intent, requestCode : Int, bundle : Bundle?) -> Unit
    lateinit var fragmentManager : ApplicationFragmentManager

    fun loadInitialFragment(@IdRes id :Int, baseFragment : BaseFragment) = fragmentManager.loadInitialFragment(id, baseFragment)
}