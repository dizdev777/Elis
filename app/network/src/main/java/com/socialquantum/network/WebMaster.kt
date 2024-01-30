package com.socialquantum.network

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.socialquantum.data.DataUtils

class WebMaster(private val context: Context, private val webView: WebView, val lottieAnimationView: LottieAnimationView) : WebViewClient() {

    private val dataUtils = DataUtils(context)

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        webView.visibility = View.VISIBLE
        lottieAnimationView.visibility = View.GONE
        Toast.makeText(context, url.toString(), Toast.LENGTH_SHORT).show()
        dataUtils.saveLastOpenedUrl(url.toString())
    }

    fun getLastOpenedUrl(url: String): String? {
        return dataUtils.getLastOpenedUrl(url)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url.toString()
        if (!isInternalLink(url)) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
            return true
        }
        view?.loadUrl(url)
        return false
    }

    private fun isInternalLink(url: String): Boolean {
        return url.startsWith("http://") || url.startsWith("https://")
    }
}