package com.pinakin.giffresh.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pinakin.giffresh.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifFreshHomeFragment : Fragment(R.layout.fragment_giffresh_home) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var gifFreshHomeAdapter: GifFreshHomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.tab)
        viewPager = view.findViewById(R.id.view_pager)

        gifFreshHomeAdapter = GifFreshHomeAdapter(this)

        viewPager.adapter = gifFreshHomeAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            var tabName = if(position == 0){ "Trending" } else { "Favourite" }
            tab.text = tabName
        }.attach()

    }
}