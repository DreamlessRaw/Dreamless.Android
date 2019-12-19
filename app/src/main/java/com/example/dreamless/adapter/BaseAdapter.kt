package com.example.dreamless.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamless.BR
import com.example.dreamless.R


abstract class BaseAdapter<T>(
    var isMore: Boolean = false,
    var data: ArrayList<T> = arrayListOf<T>()
) :
    RecyclerView.Adapter<BaseViewHolder>() {


    private var msg: String = "数据正在加载中……"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        val binding: ViewDataBinding = holder.dataBinding
        if (viewType == R.layout.item_footer) {
            binding.setVariable(BR.msg, msg)
        } else {
            binding.setVariable(BR.data, data[position])
            binding.executePendingBindings()
        }
    }


    /**
     * 总数,如果需要加载更多,count+1以适应底部的提示信息
     */
    override fun getItemCount(): Int {
        return when {
            !isMore -> data.size
            else -> if (data.size <= 0) 0 else data.size
        }
    }

    /**
     * 加载数据并刷新页面
     */
    fun refreshData(data: List<T>) {
        this.data = data as ArrayList<T>
        this.notifyDataSetChanged()
    }

    /**
     * 加载数据并刷新页面
     */
    fun addData(data: List<T>) {
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    /**
     * 正在加载中
     */
    fun more() {
        this.msg = "数据正在加载中……"
    }

    /**
     * 没有更多了
     */
    fun noMore() {
        this.msg = "没有更多数据了……"
    }


}


