package com.hacks.radish.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hacks.radish.R
import com.hacks.radish.repo.dataobject.FeedDO
import com.hacks.radish.views.FeedCardView
import kotlinx.android.synthetic.main.adapter_feed_view.view.*
import java.util.*

class FeedAdapter(val initialList : ArrayList<FeedDO>, val context : Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view)

    var list: ArrayList<FeedDO> = initialList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_feed_view, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        with(holder.itemView.feedCard) {
            render(list[position].renderModelDO)
            var showPercentage = true
            feedCard.setOnClickListener {
                if (showPercentage) {
                    feedCard.setState(FeedCardView.Companion.State.SHOW_PERCENTAGE)
                } else {
                    feedCard.setState(FeedCardView.Companion.State.DEFAULT)
                }

                showPercentage = !showPercentage
            }
        }
    }

}