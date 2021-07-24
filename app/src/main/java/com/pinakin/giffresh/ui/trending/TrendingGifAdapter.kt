package com.pinakin.giffresh.ui.trending

import android.os.Build.VERSION.SDK_INT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.pinakin.giffresh.R
import com.pinakin.giffresh.data.remote.model.TrendingGif


class TrendingGifAdapter(var trendingGif: TrendingGif) :
    RecyclerView.Adapter<TrendingGifAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgGifItem: ShapeableImageView = itemView.findViewById(R.id.gif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trending_gif, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = trendingGif.data[position].images.downsizedMedium
        val imageLoader = ImageLoader.Builder(holder.itemView.context)
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(holder.itemView.context))
                } else {
                    add(GifDecoder())
                }
            }
            .build()

        holder.imgGifItem.load(data.url, imageLoader)

    }

    override fun getItemCount(): Int {
        return trendingGif.data.size
    }
}