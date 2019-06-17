package com.hacks.radish.viewmodels

import androidx.lifecycle.MutableLiveData
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.fragments.MediaFragment
import com.hacks.radish.fragments.VotingFragment
import com.hacks.radish.repo.dataobject.GalleryDO
import com.hacks.radish.util.lazyAndroid
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryViewModel  @Inject constructor() : BaseViewModel() {

    val galleryLiveData by lazyAndroid {
        MutableLiveData<GalleryDO>()
    }

    val mediaFragments by lazyAndroid {
        listOf(
            MediaFragment(galleryLiveData.value!!.imageA),
            MediaFragment(galleryLiveData.value!!.imageB),
            VotingFragment()
        )
    }

}