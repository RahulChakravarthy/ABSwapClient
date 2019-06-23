package com.hacks.radish.viewmodels

import androidx.lifecycle.MutableLiveData
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.fragments.MediaFragment
import com.hacks.radish.fragments.VotingFragment
import com.hacks.radish.repo.datamanager.VoteRepo
import com.hacks.radish.repo.dataobject.GalleryDO
import com.hacks.radish.repo.dataobject.ImagePairDO
import com.hacks.radish.repo.dataobject.VoteDO
import com.hacks.radish.util.lazyAndroid
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryViewModel  @Inject constructor(val voteRepo: VoteRepo) : BaseViewModel() {

    val galleryLiveData by lazyAndroid {
        MutableLiveData<GalleryDO>()
    }

    val mediaFragments : List<BaseFragment>
    get() {
        return listOf(
            MediaFragment(galleryLiveData.value!!.imageA),
            MediaFragment(galleryLiveData.value!!.imageB),
            VotingFragment()
        )
    }

    fun voteImagePair(voteDO: VoteDO) {
        vms.launch {
            when (voteRepo.voteImagePair(voteDO)) {
                VoteRepo.Status.VOTE_SUCCEED -> {

                }
                VoteRepo.Status.VOTE_FAILED -> {

                }
            }
        }
    }

}