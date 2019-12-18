package com.example.dreamless.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.dreamless.DataBinderMapperImpl

import com.example.dreamless.R
import com.example.dreamless.databinding.BlankFragmentBinding
import com.example.dreamless.models.User
import com.example.dreamless.models.UserViewModel

class BlankFragment : Fragment() {

    companion object {
        fun newInstance() = BlankFragment()
    }

    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding:BlankFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding=DataBindingUtil.inflate<BlankFragmentBinding>(inflater, R.layout.blank_fragment, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.userEvent.observe(this, Observer { data->dataBinding.data="数据出来了" })
        viewModel.getUser()
    }

}
