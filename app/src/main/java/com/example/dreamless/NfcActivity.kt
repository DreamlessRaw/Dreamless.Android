package com.example.dreamless

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.dreamless.tools.ToastUtils
import java.io.UnsupportedEncodingException
import java.lang.Exception

class NfcActivity : AppCompatActivity() {

    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pendingIntent: PendingIntent
    private var isRead: Boolean = true
    private val mineType = "application/fisnfc"

    private lateinit var editText: EditText
    private lateinit var readButton: Button
    private lateinit var writerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)
        editText = findViewById<EditText>(R.id.et_text);
        readButton = findViewById<Button>(R.id.btn_read)
        writerButton = findViewById<Button>(R.id.btn_writer)

        readButton.setOnClickListener { isRead = true }
        writerButton.setOnClickListener { isRead = false }
    }

    override fun onStart() {
        super.onStart()
        initNfc()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (isRead) {
            readNdefMessage(intent!!)
        } else {
            writerNfc(intent!!)
        }
    }

    private fun writerNfc(intent: Intent) {
        isRead = false
        val tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG) as Tag;
        //DisplayMessage(_intentMsg);
        val mimeRecord = NdefRecord(
            NdefRecord.TNF_MIME_MEDIA,
            mineType.toByteArray(Charsets.US_ASCII),
            byteArrayOf(0),
            editText.text.toString().toByteArray(Charsets.UTF_8)
        )

        val aar = NdefRecord.createApplicationRecord("dreamless.android")
        val ndefMessage = NdefMessage(mimeRecord, aar)
        val ndef = Ndef.get(tag);
        ndef.connect()
        if (!ndef.isWritable) {
            ToastUtils.long("标签是只读的")
            return
        } else {
            if (ndef.maxSize < ndefMessage.byteArrayLength) {
                ToastUtils.long("写入信息太长")
                return
            } else {
                try {
                    ndef.writeNdefMessage(ndefMessage)
                    ToastUtils.long("写卡成功")
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }

    }

    override fun onPause() {
        super.onPause()
        nfcAdapter.disableForegroundDispatch(this)
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null)
    }


    private fun initNfc() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (!nfcAdapter.isEnabled) return
        val intent = Intent(this, this.javaClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
    }


    @Throws(UnsupportedEncodingException::class)
    private fun readNdefMessage(intent: Intent) {
        val tagFromIntent = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        if (tagFromIntent == null) {
            ToastUtils.long("设备无任何值")
        } else {
            val rawMessages: Array<Parcelable> = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)!!
            val msg = rawMessages[0] as NdefMessage
            val hominidRecord = msg.records[0]
            val hominidName = String(hominidRecord.payload)
            editText.setText(hominidName)
            ToastUtils.long(hominidName)
        }
    }

}
