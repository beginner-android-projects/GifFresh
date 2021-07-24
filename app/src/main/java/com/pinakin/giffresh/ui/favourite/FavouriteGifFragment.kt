package com.pinakin.giffresh.ui.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.pinakin.giffresh.R
import com.pinakin.giffresh.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteGifFragment : Fragment(R.layout.fragment_favourite_gif) {

    private val favouriteGifViewModel: FavouriteGifViewModel by viewModels()

    private lateinit var favouriteGifAdapter: FavouriteGifAdapter
    private lateinit var recFavouriteGif: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recFavouriteGif = view.findViewById(R.id.rec_favourite_gif)


        favouriteGifAdapter = FavouriteGifAdapter(emptyList())
        recFavouriteGif.adapter = favouriteGifAdapter
        recFavouriteGif.layoutManager = GridLayoutManager(requireContext(),2)

        viewLifecycleOwner.lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                favouriteGifViewModel.favouriteGifs.collect {

                    favouriteGifAdapter.gifs = it
                    favouriteGifAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}