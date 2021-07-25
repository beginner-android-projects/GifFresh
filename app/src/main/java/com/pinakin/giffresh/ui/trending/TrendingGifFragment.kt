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
import com.pinakin.giffresh.R
import com.pinakin.giffresh.databinding.FragmentTrendingGifBinding
import com.pinakin.giffresh.utils.hideKeyboard
import com.pinakin.giffresh.utils.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendingGifFragment : Fragment(R.layout.fragment_trending_gif) {

    private val binding by viewBindings(FragmentTrendingGifBinding::bind)

    private val gifViewModel: GifViewModel by activityViewModels()

    private lateinit var gifAdapter: GifPagedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recTrendingGif.layoutManager = LinearLayoutManager(requireContext())
        binding.recTrendingGif.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )


        gifAdapter = GifPagedAdapter() { gifData ->
            if (gifData != null) {
                gifViewModel.saveGif(gifData)
            }
        }
        binding.recTrendingGif.adapter = gifAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                gifViewModel.gifs.collectLatest {
                    gifAdapter.submitData(it)
                }
            }
        }

        gifViewModel.fetchGifs()

    }
}