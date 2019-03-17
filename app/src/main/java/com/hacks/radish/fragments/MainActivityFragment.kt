package com.hacks.radish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_main_activity.*
import com.hacks.radish.R
import com.hacks.radish.activities.MainApplication
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.viewmodels.MainActivityViewModel
import com.hacks.radish.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivityFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val mainActivityViewModel : MainActivityViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_activity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MainApplication.mainApplicationComponent.inject(this)
        loadViewPager()
    }

    private fun loadViewPager() {
        //Attach adapter to viewpager
        viewPager.adapter = object : FragmentPagerAdapter(requireFragmentManager()) {
            override fun getItem(position: Int): Fragment {
                return mainActivityViewModel.dashboardFragments[position]
            }

            override fun getCount(): Int {
                return mainActivityViewModel.dashboardFragments.size
            }

        }

        //On Viewpager swipe
        viewPager.addOnPageChangeListener(mainActivityViewModel.getOnPageChangeListener())

        //Setup fragment switch
        mainActivityViewModel.viewPagerPositionLiveData.observe(this, Observer {
            viewPager.setCurrentItem(it, true)
        })
    }
}