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
    private lateinit var tipSearch: TextInputLayout
    private lateinit var edtSearch: TextInputEditText

    private lateinit var gifFreshHomeAdapter: GifFreshHomeAdapter

    private val trendingGifViewModel: TrendingGifViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.tab)
        viewPager = view.findViewById(R.id.view_pager)

        tipSearch = view.findViewById(R.id.tip_search)
        edtSearch = view.findViewById(R.id.edt_search)

        tipSearch.setEndIconOnClickListener {
            edtSearch.text?.clear()
            trendingGifViewModel.getTrendingGifs()
        }

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


        edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                v.hideKeyboard()
                trendingGifViewModel.search(edtSearch.text.toString())
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }
}