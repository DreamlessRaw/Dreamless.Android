package com.example.dreamless.tools

import com.google.gson.annotations.SerializedName

class ApiResult<T> {
    @SerializedName("IsSuccess")
    var isSuccess: Boolean = false
    @SerializedName("Message")
    var message: String = ""
    @SerializedName("Data")
    var data: T? = null

    constructor(isSuccess: Boolean, message: String) {
        this.isSuccess = isSuccess
        this.message = message
    }

    constructor(isSuccess: Boolean, message: String, data: T) {
        this.isSuccess = isSuccess
        this.message = message
        this.data = data
    }
}