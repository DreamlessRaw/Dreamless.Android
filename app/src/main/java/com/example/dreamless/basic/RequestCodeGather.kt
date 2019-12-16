package com.example.dreamless.basic

class RequestCodeGather {
    companion object{
        /** 扫码结果的RequestCode **/
        const val REQUEST_CODE_SCAN = 9999
        /** 扫码返回的内容定义，与CaptureActivity一致
         * {data.getStringExtra("codedContent")} **/
        const val DECODED_CONTENT = "codedContent"
        /** 相机权限的RequestCode **/
        const val REQUEST_CODE_CAMERA = 9998
    }
}