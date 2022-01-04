package com.example.pearbrowser

import android.content.Intent
import android.content.Intent.EXTRA_ORIGINATING_URI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isInvisible
import androidx.core.widget.addTextChangedListener

class BrowseActivity : AppCompatActivity() {
    var url = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)

        var webview = findViewById<WebView>(R.id.webView)
        var buttonEnter = findViewById<Button>(R.id.btn_enter)
        var editText = findViewById<EditText>(R.id.et_website)
        webview.webViewClient = WebViewClient()
        if (intent.type=="text/plain"){
            url=intent.getStringExtra(Intent.EXTRA_TEXT).toString()
        } else{
            url = intent.extras?.getString("url").toString()
        }
        editText.setText(url)
        webview.loadUrl(url)
        buttonEnter.setOnClickListener {
            buttonEnter.isEnabled=false
            url = editText.text.toString()
            if (url.lowercase().contains("https://"))
            else if (url.lowercase().contains("http://")) url =
                "https://" + url.lowercase().substring(6)
            else url = "https://${url.lowercase()}"
            webview.loadUrl(url)
        }
        editText.addTextChangedListener {
            buttonEnter.isEnabled = true
        }
    }
}