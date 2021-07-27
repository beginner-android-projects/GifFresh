package com.pinakin.giffresh.ui.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.load
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.imageview.ShapeableImageView
import com.pinakin.giffresh.R
import com.pinakin.giffresh.data.remote.model.GifData

class FavouriteGifAdapter(
    var gifs: List<GifData>,
    private val listener: (GifData) -> Unit
) : RecyclerView.Adapter<FavouriteGifAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgGifItem: ShapeableImageView = itemView.findViewById(R.id.gif)

        val checkBox: MaterialCheckBox = itemView.findViewById(R.id.checkbox_favourite)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trending_gif, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val gifData = gifs[position]

        val data = gifData.images.downsizedMedium

        holder.checkBox.isChecked = gifData.isFavourite

        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->

            if (buttonView.isPressed) {

                listener(gifData)

            }

        }

        holder.imgGifItem.load(data.url, holder.itemView.context.imageLoader) {

            placeholder(R.drawable.ic_baseline_gif_92)

            crossfade(true)

        }
    }

    override fun getItemCount(): Int {
        return gifs.size
    }
}