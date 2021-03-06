package com.androgynousis.messenger.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androgynousis.messenger.R
import com.androgynousis.messenger.model.ChatData
import com.androgynousis.messenger.view.holder.HolderDate
import com.androgynousis.messenger.view.holder.HolderMe
import com.androgynousis.messenger.view.holder.HolderYou

class ConversationRecyclerView
(private val mContext: Context,
 private val items: MutableList<ChatData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val DATE = 0
    private val YOU = 1
    private val ME = 2
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int { //More to come
        if (items[position].type == "0") {
            return DATE
        } else if (items[position].type == "1") {
            return YOU
        } else if (items[position].type == "2") {
            return ME
        }
        return -1
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(viewGroup.context)
        viewHolder = when (viewType) {
            DATE -> {
                val v1 = inflater.inflate(R.layout.layout_holder_date, viewGroup, false)
                HolderDate(v1)
            }
            YOU -> {
                val v2 = inflater.inflate(R.layout.layout_holder_you, viewGroup, false)
                HolderYou(v2)
            }
            else -> {
                val v = inflater.inflate(R.layout.layout_holder_me, viewGroup, false)
                HolderMe(v)
            }
        }
        return viewHolder
    }

    fun addItem(item: Collection<ChatData?>?) {
        items.addAll(item as Collection<ChatData>)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder.itemViewType) {
            DATE -> {
                val vh1 = viewHolder as HolderDate
                configureViewHolder1(vh1, position)
            }
            YOU -> {
                val vh2 = viewHolder as HolderYou
                configureViewHolder2(vh2, position)
            }
            else -> {
                val vh = viewHolder as HolderMe
                configureViewHolder3(vh, position)
            }
        }
    }

    private fun configureViewHolder3(vh1: HolderMe, position: Int) {
        vh1.time.text = items[position].time
        vh1.chatText.text = items[position].text
    }

    private fun configureViewHolder2(vh1: HolderYou, position: Int) {
        vh1.time.text = items[position].time
        vh1.chatText.text = items[position].text
    }

    private fun configureViewHolder1(vh1: HolderDate, position: Int) {
        vh1.date.text = items[position].text
    }

}