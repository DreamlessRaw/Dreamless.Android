package com.example.dreamless

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dreamless.tools.ToastUtils

class TestScanActivity : ScanActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_scan)
        val button: Button = this.findViewById(R.id.btn_scan)
        button.setOnClickListener { scan { code -> ToastUtils.long(code) } }

    }
}
