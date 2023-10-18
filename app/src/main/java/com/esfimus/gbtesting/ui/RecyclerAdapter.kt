package com.esfimus.gbtesting.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.esfimus.gbtesting.R
import com.esfimus.gbtesting.databinding.RecyclerviewItemBinding

class RecyclerAdapter(itemsCount: Int) : RecyclerView.Adapter<RecyclerAdapter.CustomViewHolder>() {

    private var itemsList = mutableListOf<Int>()
    private var itemClickListener: OnListItemClick? = null

    init {
        for (i in 1..itemsCount) {
            itemsList.add(i)
        }
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ui = RecyclerviewItemBinding.bind(itemView)
        private val cardView: CardView = ui.reportListCard

        fun bind(number: Int) {
            ui.cardNumber.text = number.toString()
        }

        init {
            cardView.setOnClickListener {
                itemClickListener?.onClick(absoluteAdapterPosition, cardView)
            }
        }
    }

    fun setClickListener(clickListener: OnListItemClick) {
        itemClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }
}

interface OnListItemClick {
    fun onClick(position: Int, cardView: View)
}