package tech.r7chakra.abswapclient.activities

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import tech.r7chakra.abswapclient.R
import tech.r7chakra.abswapclient.fragments.MainActivityFragment
import tech.r7chakra.abswapclient.managers.ApplicationFragmentManager
import tech.r7chakra.abswapclient.util.lazyAndroid
import tech.r7chakra.abswapclient.viewmodels.MainActivityViewModel
import tech.r7chakra.abswapclient.viewmodels.MainViewModelFactory
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

        loadStartingFragment()
        setBottomNavBar()

    }

    fun loadStartingFragment() {
        fragmentManager.loadInitialFragment(R.id.mainActivityFrameLayout, MainActivityFragment())
    }

    fun setBottomNavBar() {
        navigation.setOnNavigationItemSelectedListener(mainActivityViewModel.getNavigationBarListener())
        //Bad practice fix this later
        mainActivityViewModel.viewPagerPositionLiveData.observeForever {
            navigation.menu.getItem(it).isChecked = true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mainActivityViewModel.onActivityResult(requestCode, resultCode, data)
    }
}
