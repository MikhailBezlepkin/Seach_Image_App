package com.example.viewingimagesapp.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.viewingimagesapp.R
import com.example.viewingimagesapp.model.ImagesItem
import com.example.viewingimagesapp.screens.search.SearchFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.item_layout.view.*

class ImageAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    var listImage = emptyList<ImagesItem>()

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        holder.itemView.item_image.load(listImage[position].urls.small)



        if (listImage[position].user.name != null) {
            holder.itemView.item_tv.text = listImage[position].user.name
        } else {
            holder.itemView.item_tv.text = position.toString()
        }

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(listImage[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listImage.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ImagesItem>) {
        listImage = list
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onItemClick(imagesItem: ImagesItem)
    }
}