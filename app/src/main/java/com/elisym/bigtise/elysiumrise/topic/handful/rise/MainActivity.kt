package com.elisym.bigtise.elysiumrise.topic.handful.rise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.airbnb.lottie.LottieAnimationView
import com.elisym.bigtise.elysiumrise.R

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var lottieAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        webView = findViewById(R.id.webView)
        lottieAnimationView = findViewById(R.id.lottieAnimationView)
        val combinedData = intent.getStringExtra("combinedData")
        val webViewSetupHelper = WebViewSetupHelper(this, webView, combinedData, lottieAnimationView)
        webViewSetupHelper.setupWebView()
        webViewSetupHelper.load()
        if(webView.canGoBack()){
            webView.goBack()
        }
    }
}