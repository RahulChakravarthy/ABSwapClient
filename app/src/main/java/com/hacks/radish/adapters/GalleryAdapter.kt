package com.hacks.radish.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.util.lazyAndroid

class GalleryAdapter : FragmentStateAdapter {

    constructor(fragment : BaseFragment) : super(fragment)
    constructor(fragmentActivity : FragmentActivity) : super(fragmentActivity)
    constructor(fragmentManager: FragmentManager, lifecycle : Lifecycle) : super(fragmentManager, lifecycle)

    private val fragmentList by lazyAndroid {
        ArrayList<BaseFragment>()
    }

    fun addFragment(fragment: BaseFragment) {
        fragmentList.add(fragment)
    }


    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
       return fragmentList.size
    }

}