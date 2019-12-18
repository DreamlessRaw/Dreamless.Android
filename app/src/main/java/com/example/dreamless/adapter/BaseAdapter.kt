package com.example.dreamless.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView


    abstract class BaseAdapter<T>(var data: List<T> = listOf()) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun getItemCount(): Int = data.size

        fun refreshData(newData: List<T>) {
        this.data = newData
        this.notifyDataSetChanged()
    }
}


