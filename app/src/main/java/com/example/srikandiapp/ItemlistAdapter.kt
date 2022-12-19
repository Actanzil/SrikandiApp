package com.example.srikandiapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.srikandiapp.room.Item
import kotlinx.android.synthetic.main.itemlist.view.*

class ItemlistAdapter (var items: ArrayList<Item>, var listener: OnAdapterListener)
    : RecyclerView.Adapter<ItemlistAdapter.ItemlistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemlistViewHolder {
        return ItemlistViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.itemlist,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount() = items.size

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ItemlistViewHolder, position: Int) {
        val item = items[position]
        holder.view.item_title.text = item.nama
        holder.view.item_price.text = item.harga
        Glide.with(holder.view)
            .load(item.urlgambar)
            .into(holder.view.item_image)
        holder.view.card_view.setOnClickListener {
            listener.onClick(item)
        }
    }

    class ItemlistViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(newList: List<Item>) {
        items.clear()
        items.addAll(newList)
    }

    interface OnAdapterListener {
        fun onClick(item: Item)
    }
}