package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.RecentItemData

class RecentItemAdapter(private val list: ArrayList<RecentItemData>,private val listener: OnItemClick):RecyclerView.Adapter<RecentItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_search_item,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = list[position]

        holder.imageView.setImageResource(current.image)
        holder.name.text = current.name
        holder.price.text = "From \u20B9 ${current.price}"
    }


    inner class ItemViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView:ImageView = itemView.findViewById(R.id.recent_image)
        val name:TextView = itemView.findViewById(R.id.recent_name)
        val price:TextView = itemView.findViewById(R.id.recent_price)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position!=RecyclerView.NO_POSITION){
                listener.itemClick(position)
            }
        }

    }

    interface OnItemClick{
        fun itemClick(position: Int)
    }
}