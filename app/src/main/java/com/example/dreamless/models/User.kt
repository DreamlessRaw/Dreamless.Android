package com.example.dreamless.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dreamless.tools.RetrofitCallback
import com.example.dreamless.tools.RetrofitUtils


data class User(var Code: String, var Name: String)

open class UserViewModel : ViewModel() {
    var weatherEvent = MutableLiveData<User>()

    open fun getUser() {
        RetrofitUtils().getInstance().getUser().enqueue(object : RetrofitCallback<User> {
            override fun success(data: User) {
                weatherEvent.value = data
            }
        })
    }
}