package com.example.firstapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.CardLayoutBinding
import com.example.firstapp.model.Item
import com.example.firstapp.ui.PostClickHandler
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val clickHandler: PostClickHandler) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items: MutableList<Item> = mutableListOf()
    fun setUpdatedData(items: MutableList<Item>) {
        this.items = items
        notifyDataSetChanged()

    }

    inner class MyViewHolder(private val itemBinding: CardLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        fun bind(item: Item) {
            itemBinding.itemTitle.text = item.fullName
            itemBinding.itemDesc.text = item.description
            val imageThumb = itemBinding.itemImage
            val url = item.owner.avatarUrl

            Picasso.get()
                .load(url)
                .into(imageThumb)
        }

        init {
            itemBinding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentRepo = items[adapterPosition]
            clickHandler.clickedPostItem(currentRepo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(items[position])
    }

}