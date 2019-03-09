package tech.r7chakra.abswapclient.activities

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
}
