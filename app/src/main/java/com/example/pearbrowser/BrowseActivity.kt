package com.example.pearbrowser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class BrowseActivity : AppCompatActivity() {
    var url = ""
    lateinit var buttonEnter: Button
    lateinit var webview: WebView
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        initViews()
    }

    private fun initViews() {
        webview = findViewById<WebView>(R.id.webView)
        buttonEnter = findViewById<Button>(R.id.btn_enter)
        editText = findViewById<EditText>(R.id.et_website)
        webview.webViewClient = WebViewClient()
        initIntent()
    }

    private fun initIntent() {
        if (intent.type == "text/plain") {
            url = intent.getStringExtra(Intent.EXTRA_TEXT).toString()
        } else {
            url = intent.extras?.getString("url").toString()
        }
        if (url.contains("http")) {

            url = cutUrlFromString(url)[0]
            Log.d("url", "$url")

            editText.setText(url)
            webview.loadUrl(url)
            buttonEnter.setOnClickListener {
                url = editText.text.toString()
                url = addHttps(url)
                webview.loadUrl(url)
                buttonEnter.isEnabled = false
            }
            editText.addTextChangedListener {
                buttonEnter.isEnabled = true
            }
        } else {
            Toast.makeText(this, "Url hato ekan", Toast.LENGTH_SHORT).show()
        }


    }

    private fun addHttps(url: String): String {
        var result = url
        if (url.lowercase().contains("https://"))
        else if (url.lowercase().contains("http://")) result =
            "https://" + url.lowercase().substring(6)
        else result = "https://${url.lowercase()}"
        return result
    }

    private fun cutUrlFromString(text: String): ArrayList<String> {
        var tet = text.lowercase()
        var txt = tet.split(" ", "\n")
        var urls = ArrayList<String>()
        for (s in txt) {
            if (s.contains("https://")) {
                urls.add(s)
            }
        }
        return urls
    }


}