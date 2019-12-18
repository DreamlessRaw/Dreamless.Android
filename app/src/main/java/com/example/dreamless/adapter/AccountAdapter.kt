package com.example.dreamless.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamless.BR
import com.example.dreamless.R
import com.example.dreamless.models.User

class AccountAdapter: BaseAdapter<User>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val binding: ViewDataBinding = holder.dataBinding
        binding.setVariable(BR.data, data[position])
    }


    override fun getItemViewType(position: Int): Int {
        return R.layout.fragment_account_item
    }

    override fun getItemCount(): Int = data.size

}