package com.example.firstapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.CardLayoutBinding
import com.example.firstapp.model.Item
import com.example.firstapp.ui.PostClickHandler
import com.example.firstapp.ui.views.ImageTitleSubtitleViewState

class RecyclerViewAdapter(private val clickHandler: PostClickHandler) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private val items: MutableList<Item> = mutableListOf()
    fun setUpdatedData(items: List<Item>) {
        if(this.items.isEmpty()) {
            this.items.addAll(items)
            notifyDataSetChanged()
        }

    }

    inner class MyViewHolder(private val itemBinding: CardLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {
        fun bind(item: Item) {
            with(itemBinding) {
                imageComponent.render(
                    ImageTitleSubtitleViewState(
                        item.full_name,
                        item.description,
                        item.owner?.avatarUrl
                    )
                )
            }
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