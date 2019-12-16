package com.example.dreamless.tools

import android.widget.Toast


class ToastUtils {
    companion object {
        fun long(message: String) {
            Toast.makeText(AndroidApplication.getContext(), message, Toast.LENGTH_LONG).show()
        }
        fun short(message: String) {
            Toast.makeText(AndroidApplication.getContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

}