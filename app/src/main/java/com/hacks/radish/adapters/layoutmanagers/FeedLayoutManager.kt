package com.hacks.radish.adapters.layoutmanagers

import androidx.recyclerview.widget.RecyclerView
import com.stone.vega.library.VegaLayoutManager

class FeedLayoutManager : VegaLayoutManager() {

    override fun canScrollHorizontally(): Boolean = false

    override fun canScrollVertically(): Boolean = false

}