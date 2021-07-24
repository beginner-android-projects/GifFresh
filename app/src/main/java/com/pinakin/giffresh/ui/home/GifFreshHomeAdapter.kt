package com.pinakin.giffresh.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pinakin.giffresh.ui.favourite.FavouriteGifFragment
import com.pinakin.giffresh.ui.trending.TrendingGifFragment

class GifFreshHomeAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TrendingGifFragment()
            }
            1 -> {
                FavouriteGifFragment()
            }

            else -> {
                throw  IllegalStateException("Invalid state")
            }
        }

    }
}