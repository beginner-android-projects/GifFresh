package com.pinakin.giffresh.ui.trending

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pinakin.giffresh.R
import com.pinakin.giffresh.utils.hideKeyboard
import com.pinakin.giffresh.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendingGifFragment : Fragment(R.layout.fragment_trending_gif) {

    private val trendingGifViewModel: TrendingGifViewModel by activityViewModels()

    private lateinit var srfTrendingGif: SwipeRefreshLayout
    private lateinit var recTrendingGif: RecyclerView

    private lateinit var tipSearch: TextInputLayout
    private lateinit var edtSearch: TextInputEditText


    private lateinit var trendingGifAdapter: TrendingGifAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        srfTrendingGif = view.findViewById(R.id.srf_trending_gif)
        recTrendingGif = view.findViewById(R.id.rec_trending_gif)

        tipSearch = view.findViewById(R.id.tip_search)
        edtSearch = view.findViewById(R.id.edt_search)

        tipSearch.setEndIconOnClickListener {
            edtSearch.text?.clear()
            trendingGifViewModel.getTrendingGifs()
        }

        edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                v.hideKeyboard()
                trendingGifViewModel.search(edtSearch.text.toString())
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }

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


        trendingGifViewModel.getTrendingGifs()
    }
}