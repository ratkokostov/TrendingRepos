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
import com.example.firstapp.ui.PostClickHandler
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val clickHandler : PostClickHandler) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    var items : MutableList<Item> = mutableListOf()

    fun setUpdatedData(items: MutableList<Item>){
        this.items = items
        notifyDataSetChanged()

    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        val imageThumb = view.findViewById<ImageView>(R.id.item_image)
        val tvTitle = view.findViewById<TextView>(R.id.item_title)
        val tvDesc = view.findViewById<TextView>(R.id.item_desc)

        init{
            view.setOnClickListener(this)
        }

        fun bind(data: Item) {
            tvTitle.text = data.fullName
            tvDesc.text = data.description

            val url = data.owner.avatarUrl

            Picasso.get()
                .load(url)
                .into(imageThumb)
        }

        override fun onClick(p0: View?) {
            val currentRepo = items[adapterPosition]
            clickHandler.clickedPostItem(currentRepo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount(): Int {

        return items.size
    }

}