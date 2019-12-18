package com.example.dreamless.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.dreamless.R
import com.example.dreamless.adapter.AccountAdapter
import com.example.dreamless.databinding.FragmentAccountBinding
import com.example.dreamless.models.User
import com.example.dreamless.models.UserViewModel
import com.example.dreamless.tools.AndroidApplication
import com.example.dreamless.tools.ToastUtils
import kotlinx.android.synthetic.main.fragment_account.*

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
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.userEvent.observe(this, Observer { data -> adapter.refreshData(data) })
        viewModel.getUser()
    }


    companion object {
        @JvmStatic
        fun instance() = AccountFragment()
    }
}
