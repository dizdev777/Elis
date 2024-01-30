package com.elisym.bigtise.elysiumrise.topic.handful.rise.wqfw

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.airbnb.lottie.LottieAnimationView
import com.elisym.bigtise.elysiumrise.topic.handful.rise.DataUtils

class JIFhuUFUYGWQy(private val qwhufuqwfuwqfhu: Context, private val jifqhwirwqri: WebView, val fqiwrijqwriwr: LottieAnimationView) : WebViewClient() {

    private val fiqjjiwihqhr = DataUtils(qwhufuqwfuwqfhu)

    override fun onPageFinished(wqurqwrqwuruh: WebView?, fiqwfhuqwhif: String?) {
        super.onPageFinished(wqurqwrqwuruh, fiqwfhuqwhif)
        jifqhwirwqri.visibility = View.VISIBLE
        fqiwrijqwriwr.visibility = View.GONE
        fiqjjiwihqhr.saveLastOpenedUrl(fiqwfhuqwhif.toString())
    }

    fun fjiqwirhwqirjiqwr(url: String): String? {
        return fiqjjiwihqhr.getLastOpenedUrl(url)
    }

    override fun shouldOverrideUrlLoading(fijqwihfjiqrji: WebView?, fijqwirqwjriwr: WebResourceRequest?): Boolean {
        val fiqwhuuqruqwr = fijqwirqwjriwr?.url.toString()
        if (!uifquwywqurwr(fiqwhuuqruqwr)) {
            val fiqwjhqiriqwr = Intent(Intent.ACTION_VIEW, Uri.parse(fiqwhuuqruqwr))
            if (fiqwjhqiriqwr.resolveActivity(qwhufuqwfuwqfhu.packageManager) != null) {
                qwhufuqwfuwqfhu.startActivity(fiqwjhqiriqwr)
            }
            return true
        }
        fijqwihfjiqrji?.loadUrl(fiqwhuuqruqwr)
        return false
    }

    private fun uifquwywqurwr(url: String): Boolean {
        return url.startsWith("http://") || url.startsWith("https://")
    }
}