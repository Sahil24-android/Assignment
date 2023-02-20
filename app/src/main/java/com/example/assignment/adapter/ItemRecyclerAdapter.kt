package com.example.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.ItemListDataClass

class ItemRecyclerAdapter(private val list: ArrayList<ItemListDataClass>):RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_recycler_view,parent,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = list[position]

        holder.imageView.setImageResource(current.image)
        holder.imageType.text = current.type
    }


    class ItemViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val imageView:ImageView = itemView.findViewById(R.id.item_image)
        val imageType:TextView = itemView.findViewById(R.id.item_type)

    }
}