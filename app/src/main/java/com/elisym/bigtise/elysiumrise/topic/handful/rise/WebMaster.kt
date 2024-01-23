package com.elisym.bigtise.elysiumrise.topic.handful.rise

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.airbnb.lottie.LottieAnimationView

class WebMaster (private val context: Context, private val webView: WebView, val lottieAnimationView: LottieAnimationView) : WebViewClient() {
    private val PREFS_NAME = "MyPrefsFile"
    private val KEY_LAST_OPENED_URL = "lastOpenedUrl"

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        webView.visibility = View.VISIBLE
        lottieAnimationView.visibility = View.GONE
        agdfygweuyfgwef(url.toString())
    }


    fun gfyugwueyfew(): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_LAST_OPENED_URL, null)
    }

    var l = ""
    private fun agdfygweuyfgwef(fdfyuwefwef: String) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if(l == "" && gfyugwueyfew() == null){
            l = fdfyuwefwef
            sharedPreferences.edit().putString(KEY_LAST_OPENED_URL, fdfyuwefwef).apply()
        }
    }

    fun getLastOpenedUrl(): String? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_LAST_OPENED_URL, null)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url.toString()
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
            return true
        }
        view?.loadUrl(url)
        return false
    }
}