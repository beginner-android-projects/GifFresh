package com.pinakin.giffresh.ui.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.pinakin.giffresh.R
import com.pinakin.giffresh.databinding.FragmentFavouriteGifBinding
import com.pinakin.giffresh.utils.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteGifFragment : Fragment(R.layout.fragment_favourite_gif) {

    private val favouriteGifViewModel: FavouriteGifViewModel by viewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val binding by viewBindings(FragmentFavouriteGifBinding::bind)

    private lateinit var favouriteGifAdapter: FavouriteGifAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favouriteGifAdapter = FavouriteGifAdapter(emptyList()) { gifData ->

            favouriteGifViewModel.deleteGif(gifData)

            sharedViewModel.setRefreshRequired(true)
        }

        binding.recFavouriteGif.emptyView = binding.txtMessage

        binding.recFavouriteGif.adapter = favouriteGifAdapter

        binding.recFavouriteGif.layoutManager = GridLayoutManager(requireContext(), 2)

        viewLifecycleOwner.lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                favouriteGifViewModel.favouriteGifs.collect { gifData ->

                        favouriteGifAdapter.gifs = gifData

                        favouriteGifAdapter.notifyDataSetChanged()

                }
            }
        }
    }
}