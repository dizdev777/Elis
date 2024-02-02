package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import com.airbnb.lottie.LottieAnimationView
import com.elisym.bigtise.elysiumrise.R
import com.onesignal.OneSignal

class HUIfuiwqgyqriwqur : AppCompatActivity() {
    private lateinit var fhgyuqwyrgwr: WebView
    private lateinit var wqyugwqyrywqryyqwuri: IFhuqwhgufwuqyFWUYQy
    private lateinit var uqiwuhuhruwqrhur: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
         fhgyuqwyrgwr = findViewById(R.id.webView)
        uqiwuhuhruwqrhur = findViewById(R.id.lottieAnimationView)
        val qwuihfuyqwgfyqwfui = intent.getStringExtra("LLL_EXTRA")
        wqyugwqyrywqryyqwuri =
                IFhuqwhgufwuqyFWUYQy(this@HUIfuiwqgyqriwqur, fhgyuqwyrgwr, qwuihfuyqwgfyqwfui, uqiwuhuhruwqrhur)
        OneSignal.User.pushSubscription.optIn()
        fhgyuqwyrgwr.webChromeClient = wqyugwqyrywqryyqwuri.fihqwfqwhufhuqwufi
        fhgyuqwyrgwr.webViewClient = wqyugwqyrywqryyqwuri.fijqwuhfquiwfhuw
        wqyugwqyrywqryyqwuri.setupWebView()
        fhgyuqwyrgwr.loadUrl(wqyugwqyrywqryyqwuri.fijqwuhfquiwfhuw.fjiqwirhwqirjiqwr(qwuihfuyqwgfyqwfui.toString()).toString())
    }
    override fun onBackPressed() {
        if (fhgyuqwyrgwr.canGoBack()) {
            fhgyuqwyrgwr.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
