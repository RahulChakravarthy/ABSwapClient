package tech.r7chakra.abswapclient.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import tech.r7chakra.abswapclient.R
import tech.r7chakra.abswapclient.fragments.BaseFragment
import tech.r7chakra.abswapclient.fragments.FeedFragment
import tech.r7chakra.abswapclient.fragments.PostFragment
import tech.r7chakra.abswapclient.managers.MenuManager
import tech.r7chakra.abswapclient.util.lazyAndroid
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityViewModel @Inject constructor(private val menuManager: MenuManager) : BaseViewModel() {

    val dashboardFragments : ArrayList<BaseFragment> by lazyAndroid { ArrayList<BaseFragment>() }

    val viewPagerPositionLiveData by lazyAndroid {
        MutableLiveData<Int>().apply {
            value = 0
        }
    }

    init {
        dashboardFragments.add(PostFragment())
        dashboardFragments.add(FeedFragment())
    }

    fun getNavigationBarListener() : BottomNavigationView.OnNavigationItemSelectedListener {
        return BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_upload -> {
                    viewPagerPositionLiveData.value = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_feed -> {
                    viewPagerPositionLiveData.value = 1
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
        }
    }

    fun getOnPageChangeListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                viewPagerPositionLiveData.value = position
            }

        }
    }
}