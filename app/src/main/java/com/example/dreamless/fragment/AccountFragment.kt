package com.example.dreamless.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamless.R
import com.example.dreamless.adapter.AccountAdapter
import com.example.dreamless.databinding.FragmentAccountBinding
import com.example.dreamless.models.User
import com.example.dreamless.models.UserViewModel


class AccountFragment : Fragment() {

    private lateinit var dataBinding: FragmentAccountBinding
    private lateinit var viewModel: UserViewModel
    private val adapter = AccountAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

        val llm = LinearLayoutManager(context)
        llm.orientation = RecyclerView.VERTICAL
        dataBinding.rcvAccount.layoutManager = llm

        dataBinding.rcvAccount.adapter = adapter

        dataBinding.rcvAccount.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // 这里在加入判断，判断是否滑动到底部
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (adapter.data.size <= 100) {
                        var dataNew = arrayListOf<User>()
                        adapter.more()
                        for (i in adapter.data.size..adapter.data.size + 10) {
                            dataNew.add(User(i.toString(), "$i 号"))
                        }
                        adapter.addData(dataNew)
                    }
                    if (adapter.data.size > 100) {
                        adapter.noMore()
                    }
                }
            }
        })

        dataBinding.srlAccount.setOnRefreshListener { viewModel.getUser() }

        return dataBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.userEvent.observe(this, Observer { data ->
            adapter.refreshData(data)
            dataBinding.srlAccount.isRefreshing = false
        })
        viewModel.getUser()
    }


    companion object {
        @JvmStatic
        fun instance() = AccountFragment()
    }
}
