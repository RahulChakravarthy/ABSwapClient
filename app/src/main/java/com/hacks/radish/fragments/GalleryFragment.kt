package com.hacks.radish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.hacks.radish.R
import com.hacks.radish.activities.MainApplication
import com.hacks.radish.adapters.ScreenSlidePagerAdapter
import com.hacks.radish.repo.dataobject.GalleryDO
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.viewmodels.GalleryViewModel
import com.hacks.radish.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_gallery.*
import javax.inject.Inject

class GalleryFragment(val galleryDO: GalleryDO) : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val gvm: GalleryViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(GalleryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MainApplication.mainApplicationComponent.inject(this)
        //Temporary solution
        gvm.galleryLiveData.value = galleryDO
        galleryViewPager.adapter = ScreenSlidePagerAdapter(gvm.mediaFragments, childFragmentManager)
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}