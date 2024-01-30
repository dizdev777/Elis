package com.elisym.bigtise.elysiumrise.topic.handful.qwqwq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elisym.bigtise.elysiumrise.R
import com.onesignal.OneSignal

class ZaglushkaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zaglushka)
        OneSignal.User.pushSubscription.optOut()
    }
}