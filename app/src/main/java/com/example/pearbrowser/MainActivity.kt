package com.example.pearbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.URLUtil
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity() {
    lateinit var url: String
    val TAG = MainActivity::class.java.simpleName
    lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews() {
        editText = findViewById(R.id.et_website_home)
    }

    fun enterWebPage(view: View) {
        url = editText.text.toString()
        val intent = Intent(this, BrowseActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
        Log.d(TAG, url);
    }

    fun shareUrl(view: android.view.View) {
        url = editText.text.toString()
        shareUrl(url)
    }

    fun shareUrl(text: String?) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share"))
    }
}