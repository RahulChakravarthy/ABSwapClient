package com.hacks.radish.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hacks.radish.R
import com.hacks.radish.repo.dataobject.FeedDO
import com.hacks.radish.views.PercentageFilterView

class FeedAdapter(val list : ArrayList<FeedDO>, val context : Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        val percentageFilterView1 = view.findViewById<PercentageFilterView>(R.id.percentageView1)
        val percentageFilterView2 = view.findViewById<PercentageFilterView>(R.id.percentageView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.adapter_feed_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        holder.percentageFilterView1.setMainBackgroundImage(list[position].image1Url)
        holder.percentageFilterView2.setMainBackgroundImage(list[position].image2Url)
    }

}