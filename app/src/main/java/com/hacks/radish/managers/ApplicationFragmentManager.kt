package com.hacks.radish.managers

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.hacks.radish.fragments.BaseFragment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApplicationFragmentManager @Inject constructor(private val fragmentManager: FragmentManager) {

    private var containerId = -1
    private lateinit var startingFragment: BaseFragment

    fun loadInitialFragment(@IdRes containerId : Int, startingFragment : BaseFragment) {
        this.containerId = containerId
        this.startingFragment = startingFragment

        fragmentManager
            .beginTransaction()
            .add(containerId, startingFragment)
            .commit()
    }

    fun addFragment(baseFragment: BaseFragment,
                    @AnimRes enterAnimation : Int = 0,
                    @AnimRes exitAnimation: Int = 0,
                    @AnimRes popEnter : Int = 0,
                    @AnimRes popExit : Int = 0,
                    addToBackStack : Boolean = true,
                    tag : String? = null) {
        fragmentManager
            .beginTransaction()
            .add(baseFragment, tag)
            .setCustomAnimations(enterAnimation, exitAnimation, popEnter, popExit)
            .apply {
                if (addToBackStack) {
                    addToBackStack(tag)
                }
            }.commit()
    }

    fun replaceFragment(baseFragment: BaseFragment) {

    }

    fun popFragment() {
        fragmentManager
            .popBackStack()
    }
}