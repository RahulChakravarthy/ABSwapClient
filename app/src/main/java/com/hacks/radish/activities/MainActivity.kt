package com.hacks.radish.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hacks.radish.R
import com.hacks.radish.managers.ApplicationFragmentManager
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.viewmodels.MainActivityViewModel
import com.hacks.radish.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    lateinit var fragmentManager: ApplicationFragmentManager

    val mainActivityViewModel : MainActivityViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainApplication.mainApplicationComponent.inject(this)
        //For now
        fragmentManager = ApplicationFragmentManager(supportFragmentManager)

        //Give viewmodel ability to start activities with result
        mainActivityViewModel.startActivityForResultListener = { intent, requestCode, bundle -> startActivityForResult(intent, requestCode, bundle) }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mainActivityViewModel.onActivityResult(requestCode, resultCode, data)
    }
}
