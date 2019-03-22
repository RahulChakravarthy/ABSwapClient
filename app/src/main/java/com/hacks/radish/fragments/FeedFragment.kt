package com.hacks.radish.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.hacks.radish.R
import com.hacks.radish.activities.MainApplication
import com.hacks.radish.util.lazyAndroid
import com.hacks.radish.viewmodels.MainActivityViewModel
import com.hacks.radish.viewmodels.MainViewModelFactory
import com.hacks.radish.views.FeedCardView
import kotlinx.android.synthetic.main.fragment_feed.*
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

class FeedFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val mainActivityViewModel: MainActivityViewModel by lazyAndroid {
        ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        MainApplication.mainApplicationComponent.inject(this)
        //setupRecyclerView()

        //Example usage of FeedCardView
        val model = FeedCardView.RenderModel(
            "Starry Nights",
            "Edwin \"Yiu Ting\" Lo",
            listOf("Night", "Stars", "Nature").map {
                FeedCardView.RenderModel.Tag(it)
            },
            FeedCardView.RenderModel.Image("https://i.imgur.com/AmWThvw.jpg", 100),
            FeedCardView.RenderModel.Image("https://i.imgur.com/5on032B.jpg", 200)
        )
        feedCard.render(model)
        Timer().schedule(1000) {
            activity?.runOnUiThread {
                feedCard.setState(FeedCardView.Companion.State.SHOW_PERCENTAGE)
            }
        }
        Timer().schedule(2000) {
            activity?.runOnUiThread {
                feedCard.setState(FeedCardView.Companion.State.DEFAULT)
            }
        }
    }
}