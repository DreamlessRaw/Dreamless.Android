package com.example.dreamless

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.dreamless.adapter.AccountAdapter
import com.example.dreamless.fragment.AccountFragment
import com.example.dreamless.fragment.BlankFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : ScanActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vp = findViewById<ViewPager2>(R.id.vp)
        val tl = findViewById<TabLayout>(R.id.tl)
        val adapter = ViewPagerAdapter(this)
        vp.adapter = adapter

        TabLayoutMediator(
            tl,
            vp,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                kotlin.run {
                    tab.text = position.toString()
                }
            }).attach()
    }

    class ViewPagerAdapter(context: FragmentActivity) : FragmentStateAdapter(context) {

        var data: List<Any> =
            listOf(
                AccountFragment.instance()
            )

        override fun createFragment(position: Int): Fragment {
            return data[position] as Fragment
        }

        override fun getItemCount(): Int {
            return data.size
        }

    }
}
