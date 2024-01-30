package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.elisym.bigtise.elysiumrise.R

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var webViewSetupHelper: WebViewSetupHelper
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
         webView = findViewById(R.id.webView)
        lottieAnimationView = findViewById(R.id.lottieAnimationView)
        val combinedData = intent.getStringExtra("LLL_EXTRA")
        webViewSetupHelper =
                WebViewSetupHelper(this@MainActivity, webView, combinedData, lottieAnimationView)
        webView.webChromeClient = webViewSetupHelper.webJun
        webView.webViewClient = webViewSetupHelper.webMaster
        webViewSetupHelper.setupWebView()
        webView.loadUrl(webViewSetupHelper.webMaster.getLastOpenedUrl(combinedData.toString()).toString())
    }
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
