package com.android.samples.kotlin.basic.weather

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *Author:linhu
 *Email:lhzheng@grandstream.cn
 *Date:19-3-11
 */
class ForecastListAdapter(val items: List<String>) : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))//To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position] //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}