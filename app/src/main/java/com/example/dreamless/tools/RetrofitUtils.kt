package com.example.dreamless.tools

import android.os.Handler
import android.os.Message
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



/**
 * Retrofit辅助类
 */
class RetrofitUtils {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.200/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()

    private fun getOkHttpClient(): OkHttpClient {
        // 定制OkHttp
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        return httpClientBuilder.build()
    }

    fun getInstance(): ApiService {
        return retrofit.create()
    }

}

/**
 *Callback的封装
 */
interface RetrofitCallback<T> : Callback<ApiResult<T>> {
    override fun onResponse(call: Call<ApiResult<T>>, response: Response<ApiResult<T>>) {
        when (response.code()) {
            200 -> kotlin.run {
                val result: ApiResult<T> = response.body()!!
                if (!result.isSuccess) {
                    failed(result.message)
                } else {
                    success(result.data!!)
                }
            }
            401 -> failed("无权限!!!")
            403 -> failed("服务器拒绝访问!!!")
            404 -> failed("请求地址无效!!!")
            500 -> failed("服务器请求异常!!!")
            else -> failed("服务器请求异常!!!")
        }
    }

    override fun onFailure(call: Call<ApiResult<T>>, t: Throwable) {
        failed("网络异常,请移动到网络良好处!!!")
    }

    fun success(data: T) {
        val msg = Message()
        msg.what = 1
        msg.obj = "操作成功"
        handler.sendMessage(msg)
    }

    fun failed(message: String) {
        val msg = Message()
        msg.what = 1
        msg.obj = message
        handler.sendMessage(msg)

    }

    private val handler: Handler
        get() = Handler {
            when (it.what) {
                1 -> ToastUtils.short(it.obj.toString())
                else -> ToastUtils.short(it.obj.toString())
            }
            false
        }
}
