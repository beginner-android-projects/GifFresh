package com.pinakin.giffresh

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pinakin.giffresh.databinding.ActivityMainBinding
import com.pinakin.giffresh.ui.favourite.FavouriteGifFragment
import com.pinakin.giffresh.ui.trending.GifViewModel
import com.pinakin.giffresh.ui.trending.TrendingGifFragment
import com.pinakin.giffresh.utils.hideKeyboard
import com.pinakin.giffresh.utils.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBindings(ActivityMainBinding::bind)

    private val gifViewModel: GifViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        binding.tipSearch.setEndIconOnClickListener {
            binding.edtSearch.text?.clear()
            gifViewModel.fetchGifs()
        }

        binding.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                v.hideKeyboard()
                gifViewModel.fetchGifs(binding.edtSearch.text.toString())
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }

        setUpTabs()

    }

    private fun setUpTabs() {

        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            var tabConfig = if (position == 0) {
                Pair("Trending", R.drawable.ic_baseline_trending_up_24)
            } else {
                Pair("Favourite", R.drawable.ic_baseline_favorite_24)
            }
            tab.text = tabConfig.first
            tab.setIcon(tabConfig.second)
        }.attach()

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.toolBar.visibility = if (tab?.position == 1) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

    }

    inner class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

        private val fragments = listOf(
            TrendingGifFragment(),
            FavouriteGifFragment()
        )

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }

}