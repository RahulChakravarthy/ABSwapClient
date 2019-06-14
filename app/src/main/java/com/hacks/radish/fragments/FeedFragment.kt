package com.hacks.radish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hacks.radish.R
import com.hacks.radish.activities.MainApplication
import com.hacks.radish.adapters.FeedAdapter
import com.hacks.radish.repo.dataobject.ImagePairDO
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.viewmodels.MainActivityViewModel
import com.hacks.radish.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject

class FeedFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val mainActivityViewModel: MainActivityViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    var feedAdapter : FeedAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MainApplication.mainApplicationComponent.inject(this)
        observeFeedItems()
    }

    private fun observeFeedItems() {
        mainActivityViewModel.feedListLiveData.observe(this, Observer {
            if (feedAdapter == null) {
                setupRecyclerView(it)
            } else {
                feedAdapter?.list = it
            }
        })
        mainActivityViewModel.fetchNewFeed(5) //Fetch a new feed
    }

    private fun setupRecyclerView(list : List<ImagePairDO>) {
        feedRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        feedRecyclerView.adapter = FeedAdapter(list, requireContext())
    }
}