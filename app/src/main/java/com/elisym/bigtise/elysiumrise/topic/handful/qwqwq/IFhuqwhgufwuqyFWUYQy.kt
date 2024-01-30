package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.elisym.bigtise.elysiumrise.topic.handful.rise.wqfw.HFuqwyfguqwr
import com.elisym.bigtise.elysiumrise.topic.handful.rise.wqfw.JIFhuUFUYGWQy
import com.elisym.bigtise.elysiumrise.topic.handful.rise.wqfw.configureWebView

class IFhuqwhgufwuqyFWUYQy(private val qiwjrwqriwqr: AppCompatActivity, private val hjwqfhqwuf: WebView, private val link: String?, val fhqwirwq: LottieAnimationView) {
    val fijqwuhfquiwfhuw = JIFhuUFUYGWQy(qiwjrwqriwqr, hjwqfhqwuf, fhqwirwq)
    val fihqwfqwhufhuqwufi = HFuqwyfguqwr(qiwjrwqriwqr)
    fun setupWebView() {

        hjwqfhqwuf.webChromeClient = fihqwfqwhufhuqwufi
        hjwqfhqwuf.webViewClient = fijqwuhfquiwfhuw
        configureWebView(hjwqfhqwuf)
    }
}
