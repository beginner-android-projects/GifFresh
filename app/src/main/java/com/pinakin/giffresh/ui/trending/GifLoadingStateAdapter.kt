package com.pinakin.giffresh.ui.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.pinakin.giffresh.R

class GifLoadingStateAdapter(
    private val adapter: GifPagedAdapter
) : LoadStateAdapter<GifLoadingStateAdapter.GifLoadStateViewHolder>() {

    class GifLoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val progressBar: ContentLoadingProgressBar = itemView.findViewById(
            R.id.progress_bar
        )

        val btnRetry: MaterialButton = itemView.findViewById(R.id.retry_button)

        val txtErrorMsg: MaterialTextView = itemView.findViewById(R.id.error_msg)

    }

    override fun onBindViewHolder(holder: GifLoadStateViewHolder, loadState: LoadState) {

        holder.btnRetry.setOnClickListener {
            adapter.retry()
        }

        when (loadState) {

            is LoadState.Loading -> {
                holder.progressBar.isVisible = true
                holder.btnRetry.isVisible = false
                holder.txtErrorMsg.isVisible = true
                holder.txtErrorMsg.text = holder.itemView.context.getString(R.string.fetch_data)
            }

            is LoadState.Error -> {
                holder.btnRetry.isVisible = true
                holder.progressBar.isVisible = false
                holder.txtErrorMsg.isVisible = !loadState.error.message.isNullOrBlank()
                holder.txtErrorMsg.text = loadState.error.message
            }

            is LoadState.NotLoading -> {

            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): GifLoadStateViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.loading_state, parent, false)
        return GifLoadStateViewHolder(view)
    }
}