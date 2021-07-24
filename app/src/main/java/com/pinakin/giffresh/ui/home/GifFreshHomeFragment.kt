package com.pinakin.giffresh.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pinakin.giffresh.R
import com.pinakin.giffresh.ui.trending.TrendingGifViewModel
import com.pinakin.giffresh.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GifFreshHomeFragment : Fragment(R.layout.fragment_giffresh_home) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var gifFreshHomeAdapter: GifFreshHomeAdapter

    private val trendingGifViewModel: TrendingGifViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.tab)
        viewPager = view.findViewById(R.id.view_pager)



        gifFreshHomeAdapter = GifFreshHomeAdapter(this)

        viewPager.adapter = gifFreshHomeAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            var tabName = if (position == 0) {
                "Trending"
            } else {
                "Favourite"
            }
            tab.text = tabName
        }.attach()



    }
}