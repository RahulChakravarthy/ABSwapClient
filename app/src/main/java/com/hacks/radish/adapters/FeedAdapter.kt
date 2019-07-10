package com.hacks.radish.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hacks.radish.R
import com.hacks.radish.repo.dataobject.ImagePairDO
import com.hacks.radish.views.FeedCardView
import com.hacks.radish.views.listeners.FeedCardClickListener
import kotlinx.android.synthetic.main.adapter_feed_view.view.*
import kotlinx.android.synthetic.main.view_feed_card.view.*
import java.util.*

class FeedAdapter(private val initialList : List<ImagePairDO>, val context : Context, private val feedCardOnClickListener: FeedCardClickListener) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view)

    var list: List<ImagePairDO> = initialList
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_feed_view, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView.feedCard) {
            render(list[position].renderModelDO)
            feedCard.setOnClickListener {
                feedCardOnClickListener.onClick(feedCard)
            }
            feedCard.image_left.setOnClickListener {
                Toast.makeText(context, "IMAGE LEFT CLICKED", Toast.LENGTH_SHORT).show()
            }
            feedCard.image_right.setOnClickListener {
                Toast.makeText(context, "IMAGE RIGHT CLICKED", Toast.LENGTH_SHORT).show()
            }
        }
    }

}