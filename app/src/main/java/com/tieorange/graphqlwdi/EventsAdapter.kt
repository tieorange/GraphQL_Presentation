package com.tieorange.graphqlwdi

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fragment.EventBasic

class EventsAdapter(val context: Context) : RecyclerView.Adapter<EventViewHolder>() {

    private val eventsList = arrayListOf<EventBasic>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount() = eventsList.count()

    override fun onBindViewHolder(holder: EventViewHolder?, position: Int) {
        val event = eventsList[position]
        holder?.bind(event)
    }

    fun setData(data: List<EventBasic>) {
        eventsList.addAll(data)
        notifyDataSetChanged()
    }

}

class EventViewHolder(val itemView: View) : ViewHolder(itemView) {

    fun bind(event: EventBasic) {

    }

}
