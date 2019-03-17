package tech.r7chakra.abswapclient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import tech.r7chakra.abswapclient.R
import tech.r7chakra.abswapclient.util.lazyAndroid
import tech.r7chakra.abswapclient.viewmodels.MainActivityViewModel
import tech.r7chakra.abswapclient.viewmodels.MainViewModelFactory
import javax.inject.Inject

class FeedFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val mainActivityViewModel : MainActivityViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        mainActivityViewModel.fetchImages(3)
        //updateRecyclerView()
    }

//    private fun setupRecyclerView() {
//        feedRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        updateRecyclerView()
//    }
//
//    private fun updateRecyclerView() {
//        feedRecyclerView.adapter = FeedAdapter()
//    }


}