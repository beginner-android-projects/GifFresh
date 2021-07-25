package com.pinakin.giffresh.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.pinakin.giffresh.R
import com.pinakin.giffresh.databinding.FragmentGiffreshHomeBinding
import com.pinakin.giffresh.utils.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifFreshHomeFragment : Fragment(R.layout.fragment_giffresh_home) {

    private val binding by viewBindings(FragmentGiffreshHomeBinding::bind)

    private lateinit var gifFreshHomeAdapter: GifFreshHomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gifFreshHomeAdapter = GifFreshHomeAdapter(this)

        binding.viewPager.adapter = gifFreshHomeAdapter

        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            var tabName = if (position == 0) {
                "Trending"
            } else {
                "Favourite"
            }
            tab.text = tabName
        }.attach()

    }
}