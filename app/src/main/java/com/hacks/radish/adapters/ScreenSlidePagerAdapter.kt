package com.hacks.radish.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.fragments.MediaFragment

class ScreenSlidePagerAdapter constructor(val fragments: List<BaseFragment>, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}