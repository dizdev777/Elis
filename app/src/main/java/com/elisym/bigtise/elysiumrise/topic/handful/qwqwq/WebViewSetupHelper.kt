package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.elisym.bigtise.elysiumrise.topic.handful.rise.wqfw.WebJunior
import com.elisym.bigtise.elysiumrise.topic.handful.rise.wqfw.WebMaster
import com.elisym.bigtise.elysiumrise.topic.handful.rise.wqfw.configureWebView

class WebViewSetupHelper(private val activity: AppCompatActivity, private val webView: WebView, private val link: String?, val lottieAnimationView: LottieAnimationView) {
    val webMaster = WebMaster(activity, webView, lottieAnimationView)
    val webJun = WebJunior(activity)
    fun setupWebView() {

        webView.webChromeClient = webJun
        webView.webViewClient = webMaster
        configureWebView(webView)
    }
}
