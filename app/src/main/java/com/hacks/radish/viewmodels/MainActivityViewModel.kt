package com.hacks.radish.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.hacks.radish.R
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.fragments.FeedFragment
import com.hacks.radish.fragments.GalleryFragment
import com.hacks.radish.fragments.PostFragment
import com.hacks.radish.managers.MenuManager
import com.hacks.radish.managers.VoteManager
import com.hacks.radish.managers.SharedPreferencesManager as SPM
import com.hacks.radish.repo.api.feed.FeedRepo
import com.hacks.radish.repo.api.vote.VoteRepo
import com.hacks.radish.repo.dataobject.GalleryDO
import com.hacks.radish.repo.dataobject.ImagePairDO
import com.hacks.radish.repo.dataobject.RenderModelDO
import com.hacks.radish.repo.dataobject.VoteDO
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.views.FeedCardView
import com.hacks.radish.views.listeners.FeedCardClickListener
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityViewModel @Inject constructor(private val context : Context,
                                                private val menuManager: MenuManager,
                                                private val feedRepo: FeedRepo,
                                                private val voteRepo: VoteRepo,
                                                private val spm: SPM,
                                                private val voteManager: VoteManager
) : BaseViewModel() {

    companion object {
        const val UPLOAD_IMAGE_1_REQUEST_CODE = 10
        const val UPLOAD_IMAGE_2_REQUEST_CODE = 11
    }

    val image1UriLiveData by lazyAndroid {
        MediatorLiveData<Uri>().apply {
            value = Uri.EMPTY
        }
    }
    val image2UriLiveData by lazyAndroid {
        MediatorLiveData<Uri>().apply {
            value = Uri.EMPTY
        }
    }

    val voteLiveData by lazyAndroid {
        MediatorLiveData<VoteRepo.Status>().apply {
            addSource(voteManager.voteLiveData) {
                this.value = it
            }
        }
    }

    val feedListLiveData by lazyAndroid {
        MutableLiveData<List<ImagePairDO>>()
    }

    fun fetchNewFeed(size : Int) {
        vms.launch {
            feedListLiveData.postValue(feedRepo.getFeed(size)?.imagePairsDO)
        }
    }

    fun getFeedOnClickListener(): FeedCardClickListener {
        return object : FeedCardClickListener {
            override fun onClick(feedCardView: FeedCardView) {
                onCardClicked(feedCardView.model)

//                with(feedCardView) {
//                    val leftImageTapped = false
//                    val rightImageTapped = false
//                    val cardTapped = false
//
//                    when (state) {
//                        FeedCardView.Companion.State.TAP_RIGHT_IMAGE -> {
//                            when {
//                                leftImageTapped -> setState(FeedCardView.Companion.State.TAP_LEFT_IMAGE)
//                                rightImageTapped -> quickVoteImage("", 0)
//                                cardTapped -> onCardClicked(model)
//                            }
//                        }
//                        FeedCardView.Companion.State.TAP_LEFT_IMAGE -> {
//                            when {
//                                leftImageTapped -> quickVoteImage("", 0)
//                                rightImageTapped -> setState(FeedCardView.Companion.State.TAP_RIGHT_IMAGE)
//                                cardTapped -> onCardClicked(model)
//                            }
//                        }
//                        else -> {
//                            when {
//                                leftImageTapped -> setState(FeedCardView.Companion.State.TAP_LEFT_IMAGE)
//                                rightImageTapped -> setState(FeedCardView.Companion.State.TAP_RIGHT_IMAGE)
//                                cardTapped -> onCardClicked(model)
//                            }
//                        }
//                    }
//                }
            }
        }
    }

    private fun quickVoteImage(imagePairId : String, imageIndex : Int) {
        vms.launch {
            val voteDO = VoteDO(spm.getSessionId(),imagePairId, imageIndex)
            voteManager.voteLiveData.postValue(voteRepo.voteImagePair(voteDO))
        }
    }

    private fun onCardClicked(renderModelDO: RenderModelDO) {
        fragmentManager.pushFragment(GalleryFragment(GalleryDO(renderModelDO)))
    }

    fun onOptionsItemSelected(item: MenuItem?) : Boolean {
        when (item?.itemId) {
            R.id.uploadMenuIcon -> {
                fragmentManager.pushFragment(PostFragment())
            }
        }
        return true
    }

    fun onUploadImage1Clicked() {
        chooseImage(UPLOAD_IMAGE_1_REQUEST_CODE)
    }

    fun onUploadImage2Clicked() {
        chooseImage(UPLOAD_IMAGE_2_REQUEST_CODE)
    }

    private fun chooseImage(requestCode : Int) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResultListener(Intent.createChooser(intent, "Select a Picture "), requestCode, null)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            //Activity result is valid
            when (requestCode) {
                UPLOAD_IMAGE_1_REQUEST_CODE -> {
                    data?.let {
                        image1UriLiveData.value = it.data
                    }
                }
                UPLOAD_IMAGE_2_REQUEST_CODE -> {
                    data?.let {
                        image2UriLiveData.value = it.data
                    }
                }
                else -> {} /* do nothing */
            }
        } else {
            /* Activity result is not valid */
        }
    }
}