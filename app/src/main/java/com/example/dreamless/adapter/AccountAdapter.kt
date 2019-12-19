package com.example.dreamless.adapter

import com.example.dreamless.R
import com.example.dreamless.models.User


class AccountAdapter : BaseAdapter<User>(true) {

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) R.layout.item_footer else R.layout.fragment_account_item
    }


}