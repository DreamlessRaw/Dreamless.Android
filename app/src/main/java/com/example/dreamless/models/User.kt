package com.example.dreamless.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
                var data1= arrayListOf<User>()
                for (i in 1..30) {
                    data1.add(User(i.toString(), "$i 号"))
                }
                userEvent.value = data1
            }
        })
    }
}