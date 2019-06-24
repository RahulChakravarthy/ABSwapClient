package com.hacks.radish.viewmodels

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.hacks.radish.R
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.fragments.FeedFragment
import com.hacks.radish.fragments.GalleryFragment
import com.hacks.radish.fragments.PostFragment
import com.hacks.radish.managers.MenuManager
import com.hacks.radish.managers.SharedPreferencesManager as SPM
import com.hacks.radish.repo.datamanager.FeedRepo
import com.hacks.radish.repo.datamanager.VoteRepo
import com.hacks.radish.repo.dataobject.GalleryDO
import com.hacks.radish.repo.dataobject.ImagePairDO
import com.hacks.radish.repo.dataobject.RenderModelDO
import com.hacks.radish.repo.dataobject.VoteDO
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.views.FeedCardView
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainActivityViewModel @Inject constructor(private val context : Context,
                                                private val menuManager: MenuManager,
                                                private val feedRepo: FeedRepo,
                                                private val voteRepo: VoteRepo,
                                                private val spm: SPM
) : BaseViewModel() {

    companion object {
        const val UPLOAD_IMAGE_1_REQUEST_CODE = 10
        const val UPLOAD_IMAGE_2_REQUEST_CODE = 11
    }

    val dashboardFragments : ArrayList<BaseFragment> by lazyAndroid { ArrayList<BaseFragment>() }
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

    val feedListLiveData by lazyAndroid {
        MutableLiveData<List<ImagePairDO>>()
    }

    val voteStatusLiveData by lazyAndroid {
        MutableLiveData<VoteRepo.Status>()
    }

    init {
        dashboardFragments.add(PostFragment())
        dashboardFragments.add(FeedFragment())
    }

    fun fetchNewFeed(size : Int) {
        vms.launch {
            feedListLiveData.postValue(feedRepo.getFeed(size)?.imagePairsDO)
        }
    }

    fun getFeedOnClickListener(): View.OnClickListener {
        return View.OnClickListener {
            with(it as FeedCardView) {
//                onClick(this)
                onCardClicked(model)
            }
        }
    }

    fun quickVoteImage(imagePairId : String, imageIndex : Int) {
        vms.launch {
            val voteDO = VoteDO(spm.getSessionId(),imagePairId, imageIndex)
            voteStatusLiveData.postValue(voteRepo.voteImagePair(voteDO))
        }
    }

    private fun onCardClicked(renderModelDO: RenderModelDO) {
        fragmentManager.pushFragment(GalleryFragment(GalleryDO(renderModelDO)))
    }

    fun onOptionsItemSelected(item: MenuItem?) : Boolean {
        when (item?.itemId) {
            R.id.uploadMenuIcon -> { /* Do Upload Item logic */}
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