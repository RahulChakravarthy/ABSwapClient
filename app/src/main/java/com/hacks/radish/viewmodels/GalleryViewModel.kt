package com.hacks.radish.viewmodels

import androidx.lifecycle.MutableLiveData
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.fragments.MediaFragment
import com.hacks.radish.fragments.VotingFragment
import com.hacks.radish.managers.VoteManager
import com.hacks.radish.repo.api.vote.VoteRepo
import com.hacks.radish.repo.dataobject.GalleryDO
import com.hacks.radish.repo.dataobject.RenderModelDO
import com.hacks.radish.repo.dataobject.VoteDO
import com.hacks.radish.util.lazyAndroid
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import com.hacks.radish.managers.SharedPreferencesManager as SPM

@Singleton
class GalleryViewModel @Inject constructor(val voteRepo: VoteRepo,
                                           val spm: SPM,
                                           val voteManager: VoteManager) : BaseViewModel() {

    val galleryLiveData by lazyAndroid {
        MutableLiveData<GalleryDO>()
    }

    val mediaFragments : List<BaseFragment>
    get() {
        return listOf(
            MediaFragment(galleryLiveData.value!!.renderModelDO.imageA.imageUrl),
            MediaFragment(galleryLiveData.value!!.renderModelDO.imageB.imageUrl),
            VotingFragment()
        )
    }

    fun voteImagePair(renderModelDO: RenderModelDO, imageIndex : Int) {
        vms.launch {
            val status = voteRepo.voteImagePair(VoteDO(spm.getSessionId(), renderModelDO.id, imageIndex))
            when (status) {
                VoteRepo.Status.VOTE_SUCCEED -> {
                    fragmentManager.popFragment() //Vote was successful pop the gallery fragment
                }
                VoteRepo.Status.VOTE_FAILED -> {

                }
                else -> {}
            }
            voteManager.voteLiveData.postValue(status)
        }
    }

}