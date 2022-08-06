package com.example.firstapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.example.firstapp.model.Item
import com.squareup.picasso.Picasso

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    var items : MutableList<Item> = mutableListOf()

    fun setUpdatedData(items: MutableList<Item>){
        this.items = items
        notifyDataSetChanged()

    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDesc = view.findViewById<TextView>(R.id.tvDesc)

        fun bind(data: Item) {
            tvTitle.text = data.fullName
            tvDesc.text = data.description

            val url = data.owner.avatarUrl

            Picasso.get()
                .load(url)
                .into(imageThumb)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount(): Int {

        return items.size
    }

}