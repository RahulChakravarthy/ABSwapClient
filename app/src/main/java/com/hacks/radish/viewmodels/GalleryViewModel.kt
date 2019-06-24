package com.hacks.radish.viewmodels

import androidx.lifecycle.MutableLiveData
import com.hacks.radish.fragments.BaseFragment
import com.hacks.radish.fragments.MediaFragment
import com.hacks.radish.fragments.VotingFragment
import com.hacks.radish.managers.SharedPreferencesManager as SPM
import com.hacks.radish.repo.datamanager.VoteRepo
import com.hacks.radish.repo.dataobject.GalleryDO
import com.hacks.radish.repo.dataobject.ImagePairDO
import com.hacks.radish.repo.dataobject.RenderModelDO
import com.hacks.radish.repo.dataobject.VoteDO
import com.hacks.radish.util.lazyAndroid
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryViewModel @Inject constructor(val voteRepo: VoteRepo,
                                           val spm: SPM) : BaseViewModel() {

    val galleryLiveData by lazyAndroid {
        MutableLiveData<GalleryDO>()
    }

    val voteLiveData by lazyAndroid {
        MutableLiveData<VoteRepo.Status>()
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
            voteLiveData.postValue(voteRepo.voteImagePair(VoteDO(spm.getSessionId(), renderModelDO.id, imageIndex)))
        }
    }

}