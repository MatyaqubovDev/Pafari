package com.example.pearbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import androidx.core.net.toUri
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity() {
    lateinit var url:String
    val TAG = MainActivity::class.java.simpleName
    lateinit var editText: EditText
    lateinit var webView: WebView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews() {
      editText = findViewById(R.id.et_website_home)

//        editText.text = findViewById<EditText>(R.id.et_website_home).text
//        webView.loadUrl(editText.text.toString())
    }

    fun enterWebPage(view: View) {
        url=editText.text.toString()
        if (url.lowercase().contains("https://"))
        else if (url.lowercase().contains("http://")) url="https://" + url.lowercase().substring(6)
        else url="https://${url.lowercase()}"
        val intent = Intent(this, BrowseActivity::class.java)
        intent.putExtra("url",url)
        startActivity(intent)
        Log.d(TAG, url);
    }


}