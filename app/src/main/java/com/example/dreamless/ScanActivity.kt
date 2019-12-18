package com.example.dreamless

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dreamless.basic.RequestCodeGather
import com.example.dreamless.zxing.android.CaptureActivity
import com.example.dreamless.tools.ToastUtils


@SuppressLint("Registered")
open class ScanActivity : AppCompatActivity() {

    private lateinit var scanCallback: (code: String) -> Unit

    fun scan(callback: (code: String) -> Unit) {
        this.scanCallback = callback
        init()
    }


    private fun init() {
        //动态权限申请
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                RequestCodeGather.REQUEST_CODE_CAMERA
            )
        } else {
            val intent = Intent(this, CaptureActivity::class.java)
            startActivityForResult(intent, RequestCodeGather.REQUEST_CODE_SCAN)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            RequestCodeGather.REQUEST_CODE_CAMERA -> kotlin.run {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    scan(this.scanCallback)
                } else {
                    ToastUtils.long("你拒绝了权限申请，可能无法打开相机扫码哟！！！")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RequestCodeGather.REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK) {
            val code = data?.getStringExtra(RequestCodeGather.DECODED_CONTENT)
            if (code.isNullOrEmpty()) {
                this.scanCallback.invoke("")
            } else {
                this.scanCallback.invoke(code)
            }
        }
    }

}