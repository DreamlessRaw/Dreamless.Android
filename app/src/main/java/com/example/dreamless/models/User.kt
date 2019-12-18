package com.example.dreamless.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dreamless.adapter.AccountAdapter
import com.example.dreamless.tools.RetrofitCallback
import com.example.dreamless.tools.RetrofitUtils


data class User(var Code: String, var Name: String)

open class UserViewModel : ViewModel() {
    var userEvent = MutableLiveData<List<User>>()

    open fun getUser() {
        RetrofitUtils().getInstance().getUser().enqueue(object : RetrofitCallback<List<User>> {
            override fun success(data: List<User>) {
                userEvent.value = data
            }

            override fun failed(message: String) {
                userEvent.value = listOf(User("2", "二号"), User("3", "三号"))
            }
        })
    }
}