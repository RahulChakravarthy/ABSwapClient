package com.hacks.radish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.hacks.radish.R
import com.hacks.radish.activities.MainApplication
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.viewmodels.GalleryViewModel
import com.hacks.radish.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_voting.*
import javax.inject.Inject

class VotingFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val gvm: GalleryViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(GalleryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_voting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MainApplication.mainApplicationComponent.inject(this)
        setupImagePair()
        onImageVote()
    }

    private fun setupImagePair() {
        Glide.with(this).load(gvm.galleryLiveData.value!!.renderModelDO.imageA.imageUrl).into(smallImageA)
        Glide.with(this).load(gvm.galleryLiveData.value!!.renderModelDO.imageB.imageUrl).into(smallImageB)
    }

    private fun onImageVote() {
        smallImageA.setOnClickListener {
            gvm.voteImagePair(gvm.galleryLiveData.value!!.renderModelDO, 0)
        }
        smallImageB.setOnClickListener {
            gvm.voteImagePair(gvm.galleryLiveData.value!!.renderModelDO, 1)
        }
    }
}