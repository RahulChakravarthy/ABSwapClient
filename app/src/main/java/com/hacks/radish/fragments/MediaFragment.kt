package com.hacks.radish.fragments

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.hacks.radish.R
import com.hacks.radish.activities.MainApplication
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.viewmodels.GalleryViewModel
import com.hacks.radish.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_media.*
import javax.inject.Inject

class MediaFragment(val asset : String) : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val gvm: GalleryViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(GalleryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_media, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MainApplication.mainApplicationComponent.inject(this)
        Glide
            .with(this)
            .asBitmap()
            .load(asset)
            .into(object : CustomViewTarget<SubsamplingScaleImageView, Bitmap>(image){
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    //Show default image
                }

                override fun onResourceCleared(placeholder: Drawable?) {
                    //Do nothing for now
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    image.setImage(ImageSource.bitmap(resource))
                }

            })

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val rootView = view
        if (rootView != null) {
            outState.putString(BUNDLE_ASSET, asset)
        }
    }

    companion object {
        private const val BUNDLE_ASSET = "asset"
    }
}