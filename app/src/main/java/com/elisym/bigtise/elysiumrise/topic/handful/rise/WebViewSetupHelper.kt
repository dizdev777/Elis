package com.elisym.bigtise.elysiumrise.topic.handful.rise

import android.app.Activity
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import com.airbnb.lottie.LottieAnimationView

class WebViewSetupHelper(private val activity: Activity, private val webView: WebView, private val link: String?, val lottieAnimationView: LottieAnimationView) {
    val webMaster = WebMaster(activity, webView, lottieAnimationView)
    fun setupWebView() {
        webView.webChromeClient = WebJunior(activity)
        webView.webViewClient = webMaster
        webView.settings.apply {
            javaScriptEnabled = true
            builtInZoomControls = true
            setSupportZoom(true)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            javaScriptCanOpenWindowsAutomatically = true
            cacheMode = WebSettings.LOAD_DEFAULT
            loadWithOverviewMode = true
            useWideViewPort = true
            domStorageEnabled = true
            allowContentAccess = true
            allowFileAccess = true
            databaseEnabled = true
            allowFileAccessFromFileURLs = true
            allowUniversalAccessFromFileURLs = true
            displayZoomControls = false
            userAgentString = userAgentString.replace("; wv", "").replace(" Version/4.0", "")
        }
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
        webView.loadUrl(load())
    }


    fun load() : String {
        return if (webMaster.getLastOpenedUrl()==null){
            link.toString()
        } else {
            webMaster.getLastOpenedUrl()!!
        }
    }
}
