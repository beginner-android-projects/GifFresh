package com.pinakin.giffresh.ui.trending

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pinakin.giffresh.R
import com.pinakin.giffresh.databinding.FragmentTrendingGifBinding
import com.pinakin.giffresh.ui.favourite.SharedViewModel
import com.pinakin.giffresh.utils.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendingGifFragment : Fragment(R.layout.fragment_trending_gif) {

    private val binding by viewBindings(FragmentTrendingGifBinding::bind)

    private val gifViewModel: GifViewModel by activityViewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var gifAdapter: GifPagedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setUpGifAdapter()

        binding.srfTrendingGif.setOnRefreshListener {

            refreshAdapter()

        }

        sharedViewModel.isRefreshRequired.observe(
            viewLifecycleOwner,
            { isRefreshRequired ->

                if (isRefreshRequired != null) {

                    refreshAdapter()

                }

            }
        )

        viewLifecycleOwner.lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                gifViewModel.gifs.collectLatest {

                    gifAdapter.submitData(it)

                    binding.srfTrendingGif.isVisible = true

                    binding.progressCircular.isVisible = false

                    binding.srfTrendingGif.isRefreshing = false
                }

            }

        }

        gifViewModel.fetchGifs()

    }

    private fun refreshAdapter() {

        gifAdapter.refresh()

        gifAdapter.notifyDataSetChanged()

    }

    private fun setUpGifAdapter() {

        binding.srfTrendingGif.isVisible = false
        binding.progressCircular.isVisible = true

        binding.recTrendingGif.layoutManager = LinearLayoutManager(requireContext())

        binding.recTrendingGif.addItemDecoration(

            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )

        )

        gifAdapter = GifPagedAdapter() { gifData ->

            if (gifData != null) {

                if (gifData.isFavourite) {

                    gifViewModel.saveGif(gifData)

                } else {

                    gifViewModel.delete(gifData)

                }

            }
        }

        binding.recTrendingGif.adapter = gifAdapter.withLoadStateHeaderAndFooter(
            header = GifLoadingStateAdapter(gifAdapter),
            footer = GifLoadingStateAdapter(gifAdapter)
        )

    }
}