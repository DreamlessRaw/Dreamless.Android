package com.example.dreamless

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dreamless.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dreamless.fragment.BlankFragment
import com.example.dreamless.fragment.BlankFragment2
import com.example.dreamless.fragment.BlankFragment3
import com.example.dreamless.models.User
import com.example.dreamless.models.UserViewModel
import com.example.dreamless.tools.ToastUtils
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.reflect.typeOf

class MainActivity : ScanActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory(application)
        ).get(UserViewModel::class.java)
        //添加数据观察
        viewModel.weatherEvent.observe(this, Observer<User> { data ->

        })
        binding.vp.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(
            binding.tl,
            binding.vp,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                kotlin.run {
                    tab.setIcon(R.drawable.btn_back_normal)
                    tab.text = position.toString()
                }
            }).attach()

        startActivity(Intent(this, NfcActivity::class.java))

    }

    fun editData(view: View) {
//        scan(object : IScanResult {
//            override fun success(code: String) {
//                ToastUtils.long("扫码结果： $code")
//                view.setBackgroundColor(Color.BLUE)
//            }
//        })


    }

    class ViewPagerAdapter(context: FragmentActivity) : FragmentStateAdapter(context) {

        var data: List<Any> =
            listOf(BlankFragment.instance, BlankFragment2.instance, BlankFragment3.instance)

        override fun createFragment(position: Int): Fragment {
            return data[position] as Fragment
        }

        override fun getItemCount(): Int {
            return data.size
        }

    }
}
