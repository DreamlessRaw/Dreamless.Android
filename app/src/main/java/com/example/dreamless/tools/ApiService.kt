package com.example.dreamless.tools

import com.example.dreamless.models.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("test")
    fun getUser(): Call<ApiResult<User>>
}