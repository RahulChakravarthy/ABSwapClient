package com.hacks.radish.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hacks.radish.R
import com.hacks.radish.repo.dataobject.FeedDO

class FeedAdapter(val list : ArrayList<FeedDO>, val context : Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    class ViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.adapter_feed_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {

    }

}