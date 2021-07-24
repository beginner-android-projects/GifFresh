package com.pinakin.giffresh.ui.trending

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pinakin.giffresh.R
import com.pinakin.giffresh.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendingGifFragment : Fragment(R.layout.fragment_trending_gif) {

    private val trendingGifViewModel: TrendingGifViewModel by viewModels()

    private lateinit var srfTrendingGif: SwipeRefreshLayout
    private lateinit var recTrendingGif: RecyclerView
    private lateinit var trendingGifAdapter: TrendingGifAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        srfTrendingGif = view.findViewById(R.id.srf_trending_gif)
        recTrendingGif = view.findViewById(R.id.rec_trending_gif)


        recTrendingGif.layoutManager = LinearLayoutManager(requireContext())
        recTrendingGif.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


        viewLifecycleOwner.lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                trendingGifViewModel.trendingGifs.collect {

                    if (::trendingGifAdapter.isInitialized.not()) {
                        trendingGifAdapter = TrendingGifAdapter(it)
                        recTrendingGif.adapter = trendingGifAdapter
                    }
                    trendingGifAdapter.trendingGif = it
                    trendingGifAdapter.notifyDataSetChanged()
                }
            }
        }


        trendingGifViewModel.search("ironman")
    }
}