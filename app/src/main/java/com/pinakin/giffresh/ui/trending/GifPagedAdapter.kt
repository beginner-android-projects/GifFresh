package com.pinakin.giffresh.ui.trending

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.imageLoader
import coil.load
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.imageview.ShapeableImageView
import com.pinakin.giffresh.R
import com.pinakin.giffresh.data.remote.model.GifData

class GifPagedAdapter(
    private val listener: (GifData?) -> Unit
) : PagingDataAdapter<GifData, GifPagedAdapter.ViewHolder>(DataDifferentiator) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgGifItem: ShapeableImageView = itemView.findViewById(R.id.gif)

        val checkBox: MaterialCheckBox = itemView.findViewById(R.id.checkbox_favourite)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val gifImage = getItem(position)

        val data = gifImage?.images?.downsizedMedium


        holder.imgGifItem.load(data?.url, holder.itemView.context.imageLoader){

            placeholder(R.drawable.ic_baseline_gif_92)

            crossfade(true)

        }

        gifImage?.isFavourite?.let {

            holder.checkBox.isChecked = it

        }

        holder.checkBox.setOnCheckedChangeListener { btnView, isChecked ->

            if(btnView.isPressed){

                gifImage?.isFavourite?.let {

                    if (it != isChecked) {

                        gifImage.isFavourite = isChecked
                        listener(gifImage)

                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trending_gif, parent, false)

        return ViewHolder(view)
    }

    object DataDifferentiator : DiffUtil.ItemCallback<GifData>() {

        override fun areItemsTheSame(oldItem: GifData, newItem: GifData): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: GifData, newItem: GifData): Boolean {

            return oldItem.id == newItem.id

        }
    }
}